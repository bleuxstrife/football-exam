package com.dicoding.exam.footballexam.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dicoding.exam.footballexam.R
import com.dicoding.exam.footballexam.model.Team

class TeamOverviewFragment : Fragment() {
    private val TEAM = "team"
    fun getInstance(teams: Team): TeamOverviewFragment {
        val fra = TeamOverviewFragment()
        val bundle = Bundle()
        bundle.putSerializable(TEAM, teams)
        fra.setArguments(bundle)
        return fra
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_team_overview, container, false)
        val bundle = arguments
        if (bundle != null) {
            val teamModel = bundle.getSerializable(TEAM) as Team
            val overView = view.findViewById(R.id.team_overview) as TextView
            overView.text = teamModel.teamDescription
        }
        return view
    }
}