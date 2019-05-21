package com.dicoding.exam.footballexam.model

import com.google.gson.annotations.SerializedName

data class MatchResponse(
        @SerializedName("events")
        val matchs: List<Match>,

        @SerializedName("event")
        val searchMatches: List<Match>
)