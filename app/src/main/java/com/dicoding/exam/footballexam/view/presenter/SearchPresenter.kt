package com.dicoding.exam.footballexam.view.presenter

import com.dicoding.exam.footballexam.api.ApiRepository
import com.dicoding.exam.footballexam.api.TheSportApi
import com.dicoding.exam.footballexam.model.MatchResponse
import com.dicoding.exam.footballexam.model.TeamResponse
import com.dicoding.exam.footballexam.utils.CouroutineContextProvider
import com.dicoding.exam.footballexam.view.interf.SearchView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchPresenter(private val view: SearchView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson,
                      private val contextPool: CouroutineContextProvider = CouroutineContextProvider()){
    fun searchTeam(keyword: String?){
        view.showLoading()
        async(contextPool.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportApi.searchTeamByName(keyword)), TeamResponse::class.java)
            }
            view.showTeam(data.await().teams)
            view.hideLoading()
        }
    }
    fun searchMatch(keyword: String?){
        view.showLoading()
        async(contextPool.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportApi.searchEventByName(keyword)), MatchResponse::class.java)
            }
            view.showMatch(data.await().searchMatches)
            view.hideLoading()
        }
    }
}