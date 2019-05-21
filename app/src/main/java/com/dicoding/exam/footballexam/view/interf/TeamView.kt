package com.dicoding.exam.footballexam.view.interf

import com.dicoding.exam.footballexam.model.League
import com.dicoding.exam.footballexam.model.Match
import com.dicoding.exam.footballexam.model.Team

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun leagueShowLoading()
    fun leagueHideLoading()
    fun showTeamByLeague(data : List<Team>)
    fun showAllLeague(data : List<League>)
}