package com.dicoding.exam.footballexam.view.interf

import com.dicoding.exam.footballexam.model.Match
import com.dicoding.exam.footballexam.model.Team

interface SearchView{
    fun showLoading()
    fun hideLoading()
    fun showTeam(data: List<Team>)
    fun showMatch(data: List<Match>)
}