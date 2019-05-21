package com.dicoding.exam.footballexam.presenter

import com.dicoding.exam.footballexam.TestContextProvider
import com.dicoding.exam.footballexam.api.ApiRepository
import com.dicoding.exam.footballexam.api.TheSportApi
import com.dicoding.exam.footballexam.model.*
import com.dicoding.exam.footballexam.view.interf.SearchView
import com.dicoding.exam.footballexam.view.interf.TeamPlayerView
import com.dicoding.exam.footballexam.view.presenter.PlayerPresenter
import com.dicoding.exam.footballexam.view.presenter.SearchPresenter
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchPresenterTest{
    @Mock
    private
    lateinit var view: SearchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: SearchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = SearchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testSearchMatch(){
        val matches: MutableList<Match> = mutableListOf()
        val response = MatchResponse(matches, matches)
        val id = "Arsenal"

        Mockito.`when`(gson.fromJson(apiRepository.doRequest(TheSportApi.searchEventByName(id)),
                MatchResponse::class.java)).thenReturn(response)
        presenter.searchMatch(id)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).hideLoading()
        Mockito.verify(view).showMatch(matches)
    }

    @Test
    fun testSearchTeam(){
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val id = "Arsenal"

        Mockito.`when`(gson.fromJson(apiRepository.doRequest(TheSportApi.searchTeamByName(id)),
                TeamResponse::class.java)).thenReturn(response)
        presenter.searchTeam(id)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).hideLoading()
        Mockito.verify(view).showTeam(teams)
    }

}