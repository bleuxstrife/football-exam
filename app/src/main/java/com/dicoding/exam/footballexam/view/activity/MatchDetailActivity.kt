package com.dicoding.exam.footballexam.view.activity

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.dicoding.exam.footballexam.R
import com.dicoding.exam.footballexam.R.drawable.ic_add_favourite
import com.dicoding.exam.footballexam.R.drawable.ic_added_favourite
import com.dicoding.exam.footballexam.R.id.add_to_favorite
import com.dicoding.exam.footballexam.R.menu.detail_menu
import com.dicoding.exam.footballexam.api.ApiRepository
import com.dicoding.exam.footballexam.db.database
import com.dicoding.exam.footballexam.model.Match
import com.dicoding.exam.footballexam.model.Team
import com.dicoding.exam.footballexam.view.interf.MatchTeamView
import com.dicoding.exam.footballexam.view.presenter.MatchTeamPresenter
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_match_detail.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast
import java.lang.StringBuilder
import java.text.SimpleDateFormat

class MatchDetailActivity: AppCompatActivity(), MatchTeamView{
    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showHomeTeam(data: List<Team>) {
        Picasso.with(this).load(data[0].teamBadge).into(img_home_team)
    }

    override fun showAwayTeam(data: List<Team>) {
        Picasso.with(this).load(data[0].teamBadge).into(img_away_team)
    }

    lateinit var events : Match
    lateinit var presenter: MatchTeamPresenter
    lateinit var context: Context
    lateinit var id: String
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)
        val intent = intent
        context = this
        events = intent.extras.get("data") as Match
        id = events.eventId!!
        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchTeamPresenter(context as MatchDetailActivity, request, gson)
        presenter.getTeamAway(events.idAwayTeam)
        presenter.getTeamHome(events.idHomeTeam)
        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(Match.TABLE_EVENT_FAVOURITE,
                        Match.EVENT_ID to events.eventId,
                        Match.HOME_TEAM to events.homeTeam,
                        Match.AWAY_TEAM to events.awayTeam,
                        Match.HOME_SCORE to events.homeScore,
                        Match.AWAY_SCORE to events.awayScore,
                        Match.HOME_GOAL_DETAILS to events.homeGoalDetails,
                        Match.AWAY_GOAL_DETAILS to events.awayGoalDetails,
                        Match.HOME_SHOTS to events.homeShots,
                        Match.AWAY_SHOTS to events.awayShots,
                        Match.HOME_GOAL_KEEPER to events.homeGoalKeeper,
                        Match.HOME_DEFENSE to events.homeDefense,
                        Match.HOME_MIDFIELD to events.homeMidfield,
                        Match.HOME_FORWARD to events.homeForward,
                        Match.HOME_SUBTITUTE to events.homeSubtitutes,
                        Match.AWAY_GOAL_KEEPER to events.awayGoalKeeper,
                        Match.AWAY_DEFENSE to events.awayDefense,
                        Match.AWAY_MIDFIELD to events.awayMidfield,
                        Match.AWAY_FORWARD to events.awayForward,
                        Match.AWAY_SUBTITUTE to events.awaySubtitute,
                        Match.DATE_EVENT to events.dateEvent,
                        Match.ID_HOME_TEAM to events.idHomeTeam,
                        Match.ID_AWAY_TEAM to events.idAwayTeam)
            }
            toast("Add to Favourite")
        } catch (e: SQLiteConstraintException){
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(Match.TABLE_EVENT_FAVOURITE)
                    .whereArgs("(EVENT_ID = {id})",
                            "id" to id)
            val event = result.parseList(classParser<Match>())
            if (!event.isEmpty()) isFavorite = true else isFavorite = false
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Match.TABLE_EVENT_FAVOURITE, "(EVENT_ID = {id})",
                        "id" to id)
            }
            toast("Remove from Favourite")
        } catch (e: SQLiteConstraintException){

        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_favourite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_favourite)
    }
    fun initView(){
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolbar_title.setText("Match Schedule")
        match_date.text = convertDate(events.dateEvent!!)
        home_team.text = events.homeTeam
        away_team.text = events.awayTeam
        if (events.homeScore != null) {
            score.text = events.homeScore + "  vs  " + events.awayScore
        } else {
            score.text = "vs"
        }
        if(events.awayShots!=null){
            away_shots.text = events.awayShots
        }
        if(events.homeShots!=null){
            home_shots.text = events.homeShots
        }
        if(events.homeGoalDetails!=null){
            val parts = events.homeGoalDetails!!.split(";")
            val builder = StringBuilder()
            for (details in parts) {
                builder.append(details + "\n")
            }

            home_goals.text = builder.toString()

        }
        if(events.awayGoalDetails!=null){
            val parts = events.awayGoalDetails!!.split(";")
            val builder = StringBuilder()
            for (details in parts) {
                builder.append(details + "\n")
            }

            away_goals.text = builder.toString()
        }
        if(events.homeGoalKeeper!=null){
            val parts = events.homeGoalKeeper!!.split(";")
            val builder = StringBuilder()
            for (details in parts) {
                builder.append(details + "\n")
            }

            home_goalkeeper.text = builder.toString()
        }
        if(events.awayGoalKeeper!=null){
            val parts = events.awayGoalKeeper!!.split(";")
            val builder = StringBuilder()
            for (details in parts) {
                builder.append(details + "\n")
            }

            away_goalkeeper.text = builder.toString()
        }
        if(events.homeDefense!=null){
            val parts = events.homeDefense!!.split(";")
            val builder = StringBuilder()
            for (details in parts) {
                builder.append(details + "\n")
            }

            home_defense.text = builder.toString()
        }
        if(events.awayDefense!=null){
            val parts = events.awayDefense!!.split(";")
            val builder = StringBuilder()
            for (details in parts) {
                builder.append(details + "\n")
            }

            away_defense.text = builder.toString()
        }
        if(events.homeMidfield!=null){
            val parts = events.homeMidfield!!.split(";")
            val builder = StringBuilder()
            for (details in parts) {
                builder.append(details + "\n")
            }

            home_midfield.text = builder.toString()
        }
        if(events.awayMidfield!=null){
            val parts = events.awayMidfield!!.split(";")
            val builder = StringBuilder()
            for (details in parts) {
                builder.append(details + "\n")
            }

            away_midfield.text = builder.toString()
        }
        if(events.homeForward!=null){
            val parts = events.homeForward!!.split(";")
            val builder = StringBuilder()
            for (details in parts) {
                builder.append(details + "\n")
            }

            home_forward.text = builder.toString()
        }
        if(events.awayForward!=null){
            val parts = events.awayForward!!.split(";")
            val builder = StringBuilder()
            for (details in parts) {
                builder.append(details + "\n")
            }

            away_forward.text = builder.toString()
        }
        if(events.homeSubtitutes!=null){
            val parts = events.homeSubtitutes!!.split(";")
            val builder = StringBuilder()
            for (details in parts) {
                builder.append(details + "\n")
            }

            home_sub.text = builder.toString()
        }
        if(events.awaySubtitute!=null){
            val parts = events.awaySubtitute!!.split(";")
            val builder = StringBuilder()
            for (details in parts) {
                builder.append(details + "\n")
            }

            away_sub.text = builder.toString()
        }

    }
    fun convertDate(dateString: String): String {
        val format = SimpleDateFormat("yyyy-MM-dd")
        val format2 = SimpleDateFormat("EEE, dd MMM yyyy")
        val date = format.parse(dateString)
        val convertedDate = format2.format(date)
        return convertedDate
    }
}