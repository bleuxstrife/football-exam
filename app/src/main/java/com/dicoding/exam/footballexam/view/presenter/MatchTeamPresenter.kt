package com.dicoding.exam.footballexam.view.presenter

import com.dicoding.exam.footballexam.api.ApiRepository
import com.dicoding.exam.footballexam.api.TheSportApi
import com.dicoding.exam.footballexam.model.TeamResponse
import com.dicoding.exam.footballexam.utils.CouroutineContextProvider
import com.dicoding.exam.footballexam.view.interf.MatchTeamView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
class MatchTeamPresenter(private val view: MatchTeamView,
                              private val apiRepository: ApiRepository,
                              private val gson: Gson, private val contextPool: CouroutineContextProvider = CouroutineContextProvider()){
    fun getTeamHome(teamId: String?){
        view.showLoading()
        async(contextPool.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportApi.getTeamDetail(teamId)), TeamResponse::class.java)
            }
            view.showHomeTeam(data.await().teams)
            view.hideLoading()

        }
    }
    fun getTeamAway(teamId: String?){
        view.showLoading()
        async(contextPool.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportApi.getTeamDetail(teamId)), TeamResponse::class.java)
            }
            view.showAwayTeam(data.await().teams)
            view.hideLoading()
        }
    }
}