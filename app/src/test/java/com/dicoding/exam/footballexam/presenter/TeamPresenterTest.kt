package com.dicoding.exam.footballexam.presenter

import com.dicoding.exam.footballexam.TestContextProvider
import com.dicoding.exam.footballexam.api.ApiRepository
import com.dicoding.exam.footballexam.api.TheSportApi
import com.dicoding.exam.footballexam.model.League
import com.dicoding.exam.footballexam.model.LeagueResponse
import com.dicoding.exam.footballexam.model.Team
import com.dicoding.exam.footballexam.model.TeamResponse
import com.dicoding.exam.footballexam.view.interf.SearchView
import com.dicoding.exam.footballexam.view.interf.TeamView
import com.dicoding.exam.footballexam.view.presenter.SearchPresenter
import com.dicoding.exam.footballexam.view.presenter.TeamPresenter
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamPresenterTest{
    @Mock
    private
    lateinit var view: TeamView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: TeamPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = TeamPresenter(view, apiRepository, gson, TestContextProvider())
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

    @Test
    fun testGetTeam(){
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val id = "4328"

        Mockito.`when`(gson.fromJson(apiRepository.doRequest(TheSportApi.getTeams(id)),
                TeamResponse::class.java)).thenReturn(response)
        presenter.getTeamByLeague(id)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).hideLoading()
        Mockito.verify(view).showTeamByLeague(teams)
    }
}