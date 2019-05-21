package com.dicoding.exam.footballexam.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
import android.view.View.VISIBLE
import com.dicoding.exam.footballexam.R
import com.dicoding.exam.footballexam.R.id.*
import com.dicoding.exam.footballexam.view.fragment.FavoriteFragment
import com.dicoding.exam.footballexam.view.fragment.MatchesFragment
import com.dicoding.exam.footballexam.view.fragment.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        navigation.setOnNavigationItemSelectedListener({ item ->
            when (item.itemId) {
                item_navigation_matches -> {
                    toolbar_search.visibility = VISIBLE
                    toolbar_search.setOnClickListener{
                        val intent = Intent(ctx, SearchAcitivity::class.java)
                        intent.putExtra("data", "Match")
                        startActivity(intent)
                    }
                    loadMatchesFragment(savedInstanceState)
                }
                item_navigation_teams -> {
                    toolbar_search.visibility = VISIBLE
                    toolbar_search.setOnClickListener{
                        val intent = Intent(ctx, SearchAcitivity::class.java)
                        intent.putExtra("data", "Team")
                        startActivity(intent)
                    }
                    loadTeamsFrsgment(savedInstanceState)
                }
                item_navigation_favourite ->{
                    toolbar_search.visibility = GONE
                    loadFavoriteFragment(savedInstanceState)
                }
            }
            true
        })
        navigation.selectedItemId = item_navigation_matches
    }

    private fun loadMatchesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, MatchesFragment(), MatchesFragment::class.simpleName)
                    .commit()

        }
    }
    private fun loadTeamsFrsgment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, TeamsFragment(), TeamsFragment::class.simpleName)
                    .commit()
        }
    }

    private fun loadFavoriteFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val fragmentManager = supportFragmentManager
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, FavoriteFragment(), FavoriteFragment::class.simpleName)
                    .commit()
        }
    }

    private fun initView(){
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolbar_title.text = (getString(R.string.football_apps))
        toolbar_search.visibility = VISIBLE
    }
}
