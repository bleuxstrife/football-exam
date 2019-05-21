package com.dicoding.exam.footballexam.view.presenter

import com.dicoding.exam.footballexam.api.ApiRepository
import com.dicoding.exam.footballexam.api.TheSportApi
import com.dicoding.exam.footballexam.model.LeagueResponse
import com.dicoding.exam.footballexam.model.MatchResponse
import com.dicoding.exam.footballexam.utils.CouroutineContextProvider
import com.dicoding.exam.footballexam.view.interf.MatchView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchPresenter(private val view: MatchView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val contextPool: CouroutineContextProvider = CouroutineContextProvider()){
    fun getPrevMatch(leagueId: String?){
        view.showLoading()
        async(contextPool.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportApi.getPrevMatch(leagueId)), MatchResponse::class.java)
            }
            view.showPrevMatch(data.await().matchs)
            view.hideLoading()
        }
    }
    fun getNextMatch(leagueId: String?){
        view.showLoading()
        async(contextPool.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportApi.getNextMatch(leagueId)), MatchResponse::class.java)
            }
            view.showNextMatch(data.await().matchs)
            view.hideLoading()

        }
    }
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
}