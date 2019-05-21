package com.dicoding.exam.footballexam.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.dicoding.exam.footballexam.R
import com.dicoding.exam.footballexam.api.ApiRepository
import com.dicoding.exam.footballexam.model.Match
import com.dicoding.exam.footballexam.model.Team
import com.dicoding.exam.footballexam.utils.gone
import com.dicoding.exam.footballexam.utils.visible
import com.dicoding.exam.footballexam.view.adapter.MatchAdapter
import com.dicoding.exam.footballexam.view.adapter.TeamAdapter
import com.dicoding.exam.footballexam.view.interf.SearchView
import com.dicoding.exam.footballexam.view.presenter.SearchPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_teams_main.*
import org.jetbrains.anko.ctx

class SearchAcitivity : AppCompatActivity(), SearchView {
    override fun showLoading() {
        search_recyclerView.gone()
        search_bar.visible()
    }

    override fun hideLoading() {
        search_recyclerView.visible()
        search_bar.gone()
    }

    override fun showTeam(data: List<Team>) {
        val manager = LinearLayoutManager(this)
        search_recyclerView.hasFixedSize()
        search_recyclerView.layoutManager = manager
        val teamAdapter = TeamAdapter(this, data, object : TeamAdapter.BtnClickListener {
            override fun onBtnClick(teams: Team) {
                val intent = Intent(ctx, TeamDetailActivity::class.java)
                intent.putExtra("data", teams)
                startActivity(intent)
            }


        })
        search_recyclerView.adapter = teamAdapter
    }

    override fun showMatch(data: List<Match>) {
        var manager = LinearLayoutManager(this)
        search_recyclerView.hasFixedSize()
        search_recyclerView.layoutManager = manager
        var matchAdapter = MatchAdapter(this, data, object : MatchAdapter.BtnClickListener {
            override fun onBtnClick(event: Match) {
                val intent = Intent(ctx, MatchDetailActivity::class.java)
                intent.putExtra("data", event)
                startActivity(intent)
            }

        })
        search_recyclerView.adapter = matchAdapter
    }

    private lateinit var searchType: String
    private lateinit var presenter: SearchPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        searchType = intent.extras.get("data") as String
        initView()
    }

    private fun initView() {
        setSupportActionBar(toolbar_search)
        supportActionBar!!.setTitle("")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        search_view.setIconified(false)
        search_view.onActionViewExpanded()
        val request = ApiRepository()
        val gson = Gson()
        presenter = SearchPresenter(this, request, gson)
        search_view.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(searchType.equals("Team")){
                    presenter.searchTeam(newText)
                } else {
                    presenter.searchMatch(newText)
                }
                return true
            }

        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}