package com.dicoding.exam.footballexam.view.interf

import com.dicoding.exam.footballexam.model.Player

interface TeamPlayerView {
    fun showLoading()
    fun hideLoading()
    fun getListPlayer(data: List<Player>)
}