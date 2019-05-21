package com.dicoding.exam.footballexam.view.presenter

import com.dicoding.exam.footballexam.api.ApiRepository
import com.dicoding.exam.footballexam.api.TheSportApi
import com.dicoding.exam.footballexam.model.LeagueResponse
import com.dicoding.exam.footballexam.model.TeamResponse
import com.dicoding.exam.footballexam.utils.CouroutineContextProvider
import com.dicoding.exam.footballexam.view.interf.MatchView
import com.dicoding.exam.footballexam.view.interf.TeamView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamPresenter(private val view: TeamView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson,
                    private val contextPool: CouroutineContextProvider = CouroutineContextProvider()){

    fun getListAllLeague(){
        view.leagueShowLoading()
        async(contextPool.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportApi.getListAllLeague()), LeagueResponse::class.java)
            }
            view.showAllLeague(data.await().leagues)
            view.leagueHideLoading()

        }
    }
    fun getTeamByLeague(league: String?){
        view.showLoading()
        async(contextPool.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportApi.getTeams(league)), TeamResponse::class.java)
            }
            view.showTeamByLeague(data.await().teams)
            view.hideLoading()
        }
    }
}