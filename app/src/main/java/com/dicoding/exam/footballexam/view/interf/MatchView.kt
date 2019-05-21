package com.dicoding.exam.footballexam.view.interf

import com.dicoding.exam.footballexam.model.League
import com.dicoding.exam.footballexam.model.Match

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun leagueShowLoading()
    fun leagueHideLoading()
    fun showPrevMatch(data : List<Match>)
    fun showNextMatch(data : List<Match>)
    fun showAllLeague(data : List<League>)
}