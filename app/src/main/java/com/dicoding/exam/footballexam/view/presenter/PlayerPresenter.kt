package com.dicoding.exam.footballexam.view.presenter

import com.dicoding.exam.footballexam.api.ApiRepository
import com.dicoding.exam.footballexam.api.TheSportApi
import com.dicoding.exam.footballexam.model.PlayerResponse
import com.dicoding.exam.footballexam.utils.CouroutineContextProvider
import com.dicoding.exam.footballexam.view.interf.TeamPlayerView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerPresenter(private val view: TeamPlayerView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson,
                      private val contextPool: CouroutineContextProvider = CouroutineContextProvider()){
    fun getListPlayer(teamId: String?){
        view.showLoading()
        async(contextPool.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportApi.getAllPlayerByTeam(teamId)), PlayerResponse::class.java)
            }
            view.getListPlayer(data.await().player)
            view.hideLoading()

        }
    }
}