package com.dicoding.exam.footballexam.model

import com.google.gson.annotations.SerializedName

data class League(
        @SerializedName("idLeague")
        var idLeague: String?,
        @SerializedName("strLeague")
        var leagueName: String?,
        @SerializedName("strSport")
        var sport: String?,
        @SerializedName("strLeagueAlternate")
        var leagueNameAlternative: String?
)