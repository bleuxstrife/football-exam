package com.dicoding.exam.footballexam.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.dicoding.exam.footballexam.R
import com.dicoding.exam.footballexam.model.Player
import com.dicoding.exam.footballexam.model.Team
import com.dicoding.exam.footballexam.utils.gone
import com.dicoding.exam.footballexam.utils.visible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.acitivity_team_detail.*
import kotlinx.android.synthetic.main.activity_player_detail.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class PlayerDetailActivity: AppCompatActivity(){
    private lateinit var player: Player
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        player = intent.extras.get("data") as Player
        setContentView(R.layout.activity_player_detail)
        initView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initView(){
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolbar_title.text = player.playerName
        if(player.playerBanner!=null){
            player_banner.visible()
            Picasso.with(this).load(player.playerBanner).into(player_banner)
        } else {
            player_banner.gone()
        }
        player_position.text = player.playerPosition
        player_height.text = player.playerHeight
        player_weight.text = player.playerWeight
        player_overview.text = player.playerDescription
    }
}