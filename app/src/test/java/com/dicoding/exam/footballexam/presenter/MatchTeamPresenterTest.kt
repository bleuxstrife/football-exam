package com.dicoding.exam.footballexam.presenter

import com.dicoding.exam.footballexam.TestContextProvider
import com.dicoding.exam.footballexam.api.ApiRepository
import com.dicoding.exam.footballexam.api.TheSportApi
import com.dicoding.exam.footballexam.model.Team
import com.dicoding.exam.footballexam.model.TeamResponse
import com.dicoding.exam.footballexam.view.interf.MatchTeamView
import com.dicoding.exam.footballexam.view.interf.MatchView
import com.dicoding.exam.footballexam.view.presenter.MatchPresenter
import com.dicoding.exam.footballexam.view.presenter.MatchTeamPresenter
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchTeamPresenterTest{
    @Mock
    private
    lateinit var view: MatchTeamView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MatchTeamPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MatchTeamPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetHomeTeam(){
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val id = "133604"

        Mockito.`when`(gson.fromJson(apiRepository.doRequest(TheSportApi.getTeamDetail(id)),
                TeamResponse::class.java)).thenReturn(response)
        presenter.getTeamHome(id)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).hideLoading()
        Mockito.verify(view).showHomeTeam(teams)
    }

    @Test
    fun testGetAwayTeam(){
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val id = "133604"

        Mockito.`when`(gson.fromJson(apiRepository.doRequest(TheSportApi.getTeamDetail(id)),
                TeamResponse::class.java)).thenReturn(response)
        presenter.getTeamAway(id)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).hideLoading()
        Mockito.verify(view).showAwayTeam(teams)
    }
}