package com.dicoding.exam.footballexam.presenter

import com.dicoding.exam.footballexam.TestContextProvider
import com.dicoding.exam.footballexam.api.ApiRepository
import com.dicoding.exam.footballexam.api.TheSportApi
import com.dicoding.exam.footballexam.model.League
import com.dicoding.exam.footballexam.model.LeagueResponse
import com.dicoding.exam.footballexam.model.Match
import com.dicoding.exam.footballexam.model.MatchResponse
import com.dicoding.exam.footballexam.view.interf.MatchView
import com.dicoding.exam.footballexam.view.presenter.MatchPresenter
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchPresenterTest{
    @Mock
    private
    lateinit var view: MatchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, apiRepository, gson, TestContextProvider())
    }
    @Test
    fun testGetPrevMatch(){
        val matches: MutableList<Match> = mutableListOf()
        val response = MatchResponse(matches, matches)
        val id = "4328"

        Mockito.`when`(gson.fromJson(apiRepository.doRequest(TheSportApi.getPrevMatch(id)),
                MatchResponse::class.java)).thenReturn(response)
        presenter.getPrevMatch(id)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showPrevMatch(matches)
        Mockito.verify(view).hideLoading()
    }

    @Test
    fun testGetNextMatch(){
        val matches: MutableList<Match> = mutableListOf()
        val response = MatchResponse(matches, matches)
        val id = "4328"

        Mockito.`when`(gson.fromJson(apiRepository.doRequest(TheSportApi.getNextMatch(id)),
                MatchResponse::class.java)).thenReturn(response)
        presenter.getNextMatch(id)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showNextMatch(matches)
        Mockito.verify(view).hideLoading()
    }

    @Test
    fun testGetAllLeague(){
        val league: MutableList<League> = mutableListOf()
        val response = LeagueResponse(league)

        Mockito.`when`(gson.fromJson(apiRepository.doRequest(TheSportApi.getListAllLeague()),
                LeagueResponse::class.java)).thenReturn(response)
        presenter.getListAllLeague()
        Mockito.verify(view).leagueShowLoading()
        Mockito.verify(view).showAllLeague(league)
        Mockito.verify(view).leagueHideLoading()
    }
}