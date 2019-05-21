package com.dicoding.exam.footballexam.view.activity

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.dicoding.exam.footballexam.R
import com.dicoding.exam.footballexam.db.database
import com.dicoding.exam.footballexam.model.Match
import com.dicoding.exam.footballexam.model.Team
import com.dicoding.exam.footballexam.view.adapter.MainPagerAdapter
import com.dicoding.exam.footballexam.view.fragment.TeamOverviewFragment
import com.dicoding.exam.footballexam.view.fragment.TeamPlayerFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.acitivity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class TeamDetailActivity: AppCompatActivity(){
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    lateinit var teams : Team
    private lateinit var mainPagerAdapter: MainPagerAdapter
    private var mFragments: ArrayList<Fragment> = ArrayList()
    lateinit var id: String
    private val mTitles = arrayOf("Overview", "Player")
    private lateinit var c: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_team_detail)
        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun addToFavorite(){
        try {
            database.use {
                insert(Team.TABLE_TEAM_FAVORITE,
                        Team.TEAM_ID to teams.teamId,
                        Team.TEAM_NAME to teams.teamName,
                        Team.TEAM_BADGE to teams.teamBadge,
                        Team.TEAM_FORMED_YEAR to teams.teamFormedYear,
                        Team.TEAM_STADIUM to teams.teamStadium,
                        Team.TEAM_DESCRIPTION to teams.teamDescription)
            }
            toast("Add to Favourite")
        } catch (e: SQLiteConstraintException){
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(Team.TABLE_TEAM_FAVORITE)
                    .whereArgs("(TEAM_ID = {id})",
                            "id" to id)
            val favorite = result.parseList(classParser<Team>())
            if (!favorite.isEmpty()) isFavorite = true else isFavorite = false
        }
    }


    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Team.TABLE_TEAM_FAVORITE, "(TEAM_ID = {id})",
                        "id" to id)
            }
            toast("Remove from Favourite")
        } catch (e: SQLiteConstraintException){

        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_favourite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_favourite)
    }
    private fun initView(){
        setSupportActionBar(team_detail_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        c = this
        teams = intent.extras.get("data") as Team
        id = teams.teamId!!
        favoriteState()
        Picasso.with(this).load(teams.teamBadge).into(logo_team)
        team_detail_name.text = teams.teamName
        team_detail_year.text = teams.teamFormedYear
        team_detail_stadium.text = teams.teamStadium
        initFragments()
        initViewPager()
        initTabLayout()
    }

    private fun initFragments() {
        mFragments = ArrayList()
        mFragments.add(TeamOverviewFragment().getInstance(teams))
        mFragments.add(TeamPlayerFragment().getInstance(teams))
    }

    private fun initViewPager() {
        mainPagerAdapter = MainPagerAdapter(supportFragmentManager, mFragments, mTitles, c)
        team_detail_viewpager.adapter = mainPagerAdapter
        team_detail_viewpager.offscreenPageLimit = mTitles.size
    }

    private fun initTabLayout() {
        team_detail_tab.setupWithViewPager(team_detail_viewpager)
        for (i in mTitles.indices) {
            val tab = team_detail_tab.getTabAt(i)
            tab!!.setCustomView(mainPagerAdapter.getTabView(i))
        }
    }

}