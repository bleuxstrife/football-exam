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
import android.widget.ProgressBar
import com.dicoding.exam.footballexam.R
import com.dicoding.exam.footballexam.api.ApiRepository
import com.dicoding.exam.footballexam.model.Player
import com.dicoding.exam.footballexam.model.Team
import com.dicoding.exam.footballexam.utils.gone
import com.dicoding.exam.footballexam.utils.visible
import com.dicoding.exam.footballexam.view.activity.PlayerDetailActivity
import com.dicoding.exam.footballexam.view.activity.TeamDetailActivity
import com.dicoding.exam.footballexam.view.adapter.PlayerAdapter
import com.dicoding.exam.footballexam.view.interf.TeamPlayerView
import com.dicoding.exam.footballexam.view.presenter.PlayerPresenter
import com.google.gson.Gson

class TeamPlayerFragment: Fragment(), TeamPlayerView {
    override fun showLoading() {
        bar.visible()
        listData.gone()
    }

    override fun hideLoading() {
        bar.gone()
        listData.visible()
    }

    override fun getListPlayer(data: List<Player>) {
        var manager = LinearLayoutManager(activity)
        listData.hasFixedSize()
        listData.layoutManager = manager
        var adapter = PlayerAdapter((activity as AppCompatActivity), data, object : PlayerAdapter.BtnClickListener {
            override fun onBtnClick(player: Player) {
                val intent = Intent(context, PlayerDetailActivity::class.java)
                intent.putExtra("data", player)
                startActivity(intent)
            }


        })
        listData.adapter = adapter
    }

    private val TEAM = "team"
    private lateinit var teamModel: Team
    private lateinit var presenter: PlayerPresenter
    private lateinit var bar: ProgressBar
    private lateinit var listData: RecyclerView
    fun getInstance(teams: Team): TeamPlayerFragment {
        val fra = TeamPlayerFragment()
        val bundle = Bundle()
        bundle.putSerializable(TEAM, teams)
        fra.setArguments(bundle)
        return fra
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_team_player, container, false)
        val bundle = arguments
        if (bundle != null) {
            teamModel = bundle.getSerializable(TEAM) as Team
            bar = view.findViewById(R.id.bar_player) as ProgressBar
            listData = view.findViewById(R.id.player_recyclerview) as RecyclerView
            val request = ApiRepository()
            val gson = Gson()
            presenter = PlayerPresenter(this, request, gson)
            presenter.getListPlayer(teamModel.teamId)
        }
        return view
    }
}