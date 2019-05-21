package com.dicoding.exam.footballexam.view.interf

import com.dicoding.exam.footballexam.model.Team

interface MatchTeamView {
    fun showLoading()
    fun hideLoading()
    fun showHomeTeam(data: List<Team>)
    fun showAwayTeam(data: List<Team>)
}