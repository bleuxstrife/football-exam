package com.dicoding.exam.footballexam.view.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.dicoding.exam.footballexam.R
import com.dicoding.exam.footballexam.api.ApiRepository
import com.dicoding.exam.footballexam.model.League
import com.dicoding.exam.footballexam.model.Team
import com.dicoding.exam.footballexam.utils.gone
import com.dicoding.exam.footballexam.utils.visible
import com.dicoding.exam.footballexam.view.activity.TeamDetailActivity
import com.dicoding.exam.footballexam.view.adapter.TeamAdapter
import com.dicoding.exam.footballexam.view.interf.TeamView
import com.dicoding.exam.footballexam.view.presenter.TeamPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_teams_main.*
import org.jetbrains.anko.support.v4.ctx

class TeamsFragment : Fragment(), TeamView{
    override fun showLoading() {
        bar_team.visible()
        team_recyclerview.gone()
    }

    override fun hideLoading() {
        bar_team.gone()
        team_recyclerview.visible()
    }

    override fun leagueShowLoading() {
        bar_team.visible()
        layout_spinner_team.gone()
    }

    override fun leagueHideLoading() {
        bar_team.gone()
        layout_spinner_team.visible()
    }

    override fun showTeamByLeague(data: List<Team>) {
        val manager = LinearLayoutManager(activity)
        team_recyclerview.hasFixedSize()
        team_recyclerview.layoutManager = manager
        val teamAdapter = TeamAdapter((activity as AppCompatActivity), data, object : TeamAdapter.BtnClickListener {
            override fun onBtnClick(teams: Team) {
                val intent = Intent(context,TeamDetailActivity::class.java)
                intent.putExtra("data", teams)
                startActivity(intent)
            }


        })
        team_recyclerview.adapter = teamAdapter
    }

    override fun showAllLeague(data: List<League>) {
        var spinneiItem = arrayListOf<String>()
        for (i in data) {
            spinneiItem.add(i.leagueName.toString())
        }
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinneiItem)
        spinner_team.adapter = spinnerAdapter
        spinner_team.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var leagueName = data[position].leagueName
                presenter.getTeamByLeague(leagueName)
            }

        }
    }

    private lateinit var presenter: TeamPresenter
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamPresenter(this, request, gson)
        presenter.getListAllLeague()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view  =  inflater.inflate(R.layout.fragment_teams_main, container, false)
        return view
    }
}