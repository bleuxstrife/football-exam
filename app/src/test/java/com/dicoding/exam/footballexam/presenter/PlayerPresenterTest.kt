package com.dicoding.exam.footballexam.presenter

import com.dicoding.exam.footballexam.TestContextProvider
import com.dicoding.exam.footballexam.api.ApiRepository
import com.dicoding.exam.footballexam.api.TheSportApi
import com.dicoding.exam.footballexam.model.Player
import com.dicoding.exam.footballexam.model.PlayerResponse
import com.dicoding.exam.footballexam.model.Team
import com.dicoding.exam.footballexam.model.TeamResponse
import com.dicoding.exam.footballexam.view.interf.MatchTeamView
import com.dicoding.exam.footballexam.view.interf.TeamPlayerView
import com.dicoding.exam.footballexam.view.presenter.MatchTeamPresenter
import com.dicoding.exam.footballexam.view.presenter.PlayerPresenter
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PlayerPresenterTest{
    @Mock
    private
    lateinit var view: TeamPlayerView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: PlayerPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = PlayerPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetListPlayer(){
        val players: MutableList<Player> = mutableListOf()
        val response = PlayerResponse(players)
        val id = "133604"

        Mockito.`when`(gson.fromJson(apiRepository.doRequest(TheSportApi.getAllPlayerByTeam(id)),
                PlayerResponse::class.java)).thenReturn(response)
        presenter.getListPlayer(id)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).hideLoading()
        Mockito.verify(view).getListPlayer(players)
    }
}