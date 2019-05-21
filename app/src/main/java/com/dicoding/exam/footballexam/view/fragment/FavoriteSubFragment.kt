package com.dicoding.exam.footballexam.view.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import com.dicoding.exam.footballexam.R
import com.dicoding.exam.footballexam.api.ApiRepository
import com.dicoding.exam.footballexam.db.database
import com.dicoding.exam.footballexam.model.Match
import com.dicoding.exam.footballexam.model.Team
import com.dicoding.exam.footballexam.view.activity.MatchDetailActivity
import com.dicoding.exam.footballexam.view.activity.TeamDetailActivity
import com.dicoding.exam.footballexam.view.adapter.MatchAdapter
import com.dicoding.exam.footballexam.view.adapter.TeamAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_favorite_sub_main.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.ctx

class FavoriteSubFragment: Fragment(){
    private val ARG_TITLE = "title"
    private var mTitle: String? = null
    private lateinit var listData: RecyclerView
    fun getInstance(title: String): FavoriteSubFragment {
        val fra = FavoriteSubFragment()
        val bundle = Bundle()
        bundle.putString(ARG_TITLE, title)
        fra.setArguments(bundle)
        return fra
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorite_sub_main, container, false)
        val bundle = arguments
        if (bundle != null) {
            mTitle = bundle.getString(ARG_TITLE)
        }
        listData = view.findViewById(R.id.favorite_recyclerview) as RecyclerView
        return view
    }
    private fun initView() {
        when (mTitle) {
            "Matches" -> {
                displayMatch()
            }
            "Teams" -> {
                displayTeam()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        initView()
    }

    private fun displayMatch(){
        context?.database?.use {
            val result = select(Match.TABLE_EVENT_FAVOURITE)
            val favorite = result.parseList(classParser<Match>())
            val eventFavorite = favorite
            var manager = LinearLayoutManager(activity)
            listData.hasFixedSize()
            listData.layoutManager = manager
            var matchAdapter = MatchAdapter((activity as AppCompatActivity), eventFavorite, object : MatchAdapter.BtnClickListener {
                override fun onBtnClick(event: Match) {
                    val intent = Intent(ctx, MatchDetailActivity::class.java)
                    intent.putExtra("data", event)
                    startActivity(intent)
                }

            })
            listData.adapter = matchAdapter
        }
    }

    private fun displayTeam(){
        context?.database?.use {
            val result = select(Team.TABLE_TEAM_FAVORITE)
            val favorite = result.parseList(classParser<Team>())
            val teamFavorite = favorite
            val manager = LinearLayoutManager(activity)
            listData.hasFixedSize()
            listData.layoutManager = manager
            val teamAdapter = TeamAdapter((activity as AppCompatActivity), teamFavorite, object : TeamAdapter.BtnClickListener {
                override fun onBtnClick(teams: Team) {
                    val intent = Intent(ctx, TeamDetailActivity::class.java)
                    intent.putExtra("data", teams)
                    startActivity(intent)
                }


            })
            listData.adapter = teamAdapter
        }
    }
}