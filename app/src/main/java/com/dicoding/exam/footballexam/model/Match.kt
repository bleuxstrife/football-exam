package com.dicoding.exam.footballexam.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Match (
        var id: Long ?=null,
        @SerializedName("idEvent")
        var eventId : String?=null,
        @SerializedName("strHomeTeam")
        var homeTeam : String?=null,
        @SerializedName("strAwayTeam")
        var awayTeam : String?=null,
        @SerializedName("intHomeScore")
        var homeScore : String?=null,
        @SerializedName("intAwayScore")
        var awayScore : String?=null,
        @SerializedName("strHomeGoalDetails")
        var homeGoalDetails : String?=null,
        @SerializedName("strAwayGoalDetails")
        var awayGoalDetails : String?=null,
        @SerializedName("intHomeShots")
        var homeShots : String?=null,
        @SerializedName("intAwayShots")
        var awayShots : String?=null,
        @SerializedName("strHomeLineupGoalkeeper")
        var homeGoalKeeper : String?=null,
        @SerializedName("strHomeLineupDefense")
        var homeDefense : String ?=null,
        @SerializedName("strHomeLineupMidfield")
        var homeMidfield : String ?=null,
        @SerializedName("strHomeLineupForward")
        var homeForward : String ?=null,
        @SerializedName("strHomeLineupSubstitutes")
        var homeSubtitutes : String ?=null,
        @SerializedName("strAwayLineupGoalkeeper")
        var awayGoalKeeper : String ?=null,
        @SerializedName("strAwayLineupDefense")
        var awayDefense : String ?=null,
        @SerializedName("strAwayLineupMidfield")
        var awayMidfield : String ?=null,
        @SerializedName("strAwayLineupForward")
        var awayForward : String ?=null,
        @SerializedName("strAwayLineupSubstitutes")
        var awaySubtitute : String ?=null,
        @SerializedName("dateEvent")
        var dateEvent : String?=null,
        @SerializedName("idHomeTeam")
        var idHomeTeam : String ?=null,
        @SerializedName("idAwayTeam")
        var idAwayTeam : String ?=null
) : Serializable {
    companion object {
        const val TABLE_EVENT_FAVOURITE: String = "TABLE_EVENT_FAVOURITE"
        const val ID: String ="ID"
        const val EVENT_ID: String ="EVENT_ID"
        const val HOME_TEAM: String ="HOME_TEAM"
        const val AWAY_TEAM: String ="AWAY_TEAM"
        const val HOME_SCORE: String ="HOME_SCORE"
        const val AWAY_SCORE: String ="AWAY_SCORE"
        const val HOME_GOAL_DETAILS: String = "HOME_GOAL_DETAILS"
        const val AWAY_GOAL_DETAILS: String = "AWAY_GOAL_DETAILS"
        const val HOME_SHOTS: String = "HOME_SHOTS"
        const val AWAY_SHOTS: String = "AWAY_SHOTS"
        const val HOME_GOAL_KEEPER: String = "HOME_GOAL_KEEPER"
        const val HOME_DEFENSE: String = "HOME_DEFENSE"
        const val HOME_MIDFIELD: String = "HOME_MIDFIELD"
        const val HOME_FORWARD: String = "HOME_FORWARD"
        const val HOME_SUBTITUTE: String ="HOME_SUBTITUTE"
        const val AWAY_GOAL_KEEPER: String ="AWAY_GOAL_KEEPER"
        const val AWAY_DEFENSE:  String = "AWAY_DEFENSE"
        const val AWAY_MIDFIELD: String = "AWAY_MIDFIELD"
        const val AWAY_FORWARD: String ="AWAY_FORWARD"
        const val AWAY_SUBTITUTE: String="AWAY_SUBTITUTE"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val ID_HOME_TEAM: String = "ID_HOME_TEAM"
        const val ID_AWAY_TEAM: String = "ID_AWAY_TEAM"

    }
}