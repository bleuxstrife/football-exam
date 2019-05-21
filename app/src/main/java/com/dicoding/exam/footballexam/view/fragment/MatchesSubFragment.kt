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
import android.widget.*
import com.dicoding.exam.footballexam.R
import com.dicoding.exam.footballexam.api.ApiRepository
import com.dicoding.exam.footballexam.model.League
import com.dicoding.exam.footballexam.model.Match
import com.dicoding.exam.footballexam.utils.gone
import com.dicoding.exam.footballexam.utils.invisible
import com.dicoding.exam.footballexam.utils.visible
import com.dicoding.exam.footballexam.view.activity.MatchDetailActivity
import com.dicoding.exam.footballexam.view.adapter.MatchAdapter
import com.dicoding.exam.footballexam.view.interf.MatchView
import com.dicoding.exam.footballexam.view.presenter.MatchPresenter
import com.google.gson.Gson
import org.jetbrains.anko.support.v4.ctx

class MatchesSubFragment : Fragment(), MatchView {
    override fun leagueShowLoading() {
        bar.visible()
        spinnerLayout.gone()
    }

    override fun leagueHideLoading() {
        bar.gone()
        spinnerLayout.visible()
    }

    override fun showLoading() {
        bar.visible()
        listData.invisible()
    }

    override fun hideLoading() {
        bar.gone()
        listData.visible()
    }

    override fun showPrevMatch(data: List<Match>) {
        setAdapter(data)
    }

    override fun showNextMatch(data: List<Match>) {
        setAdapter(data)
    }

    private fun setAdapter(data: List<Match>) {
        var manager = LinearLayoutManager(activity)
        listData.hasFixedSize()
        listData.layoutManager = manager
        var matchAdapter = MatchAdapter((activity as AppCompatActivity), data, object : MatchAdapter.BtnClickListener {
            override fun onBtnClick(event: Match) {
                val intent = Intent(context, MatchDetailActivity::class.java)
                intent.putExtra("data", event)
                startActivity(intent)
            }

        })
        listData.adapter = matchAdapter
    }

    override fun showAllLeague(data: List<League>) {
        var spinneiItem = arrayListOf<String>()
        for (i in data) {
            spinneiItem.add(i.leagueName.toString())
        }
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinneiItem)
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var leagueId = data[position].idLeague
                if (mTitle.equals("Next")) {
                    presenter.getNextMatch(leagueId)
                } else {
                    presenter.getPrevMatch(leagueId)
                }
            }

        }

    }

    private val ARG_TITLE = "title"
    private var mTitle: String? = null
    private lateinit var presenter: MatchPresenter
    private lateinit var listData: RecyclerView
    private lateinit var bar: ProgressBar
    private lateinit var spinner: Spinner
    private lateinit var spinnerLayout: LinearLayout
    fun getInstance(title: String): MatchesSubFragment {
        val fra = MatchesSubFragment()
        val bundle = Bundle()
        bundle.putString(ARG_TITLE, title)
        fra.setArguments(bundle)
        return fra
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_matches_sub_main, container, false)
        val bundle = arguments
        if (bundle != null) {
            mTitle = bundle.getString(ARG_TITLE)
        }
        listData = view.findViewById(R.id.match_recyclerview) as RecyclerView
        bar = view.findViewById(R.id.bar_match) as ProgressBar
        spinner = view.findViewById(R.id.spinner_league) as Spinner
        spinnerLayout = view.findViewById(R.id.layout_spinner) as LinearLayout
        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, request, gson)
        initView()
        return view
    }

    private fun initView() {
        when (mTitle) {
            "Next" -> {
                displayAllLeague()
            }
            "Prev" -> {
                displayAllLeague()
            }
        }
    }

    private fun displayAllLeague() {
        presenter.getListAllLeague()
    }
}