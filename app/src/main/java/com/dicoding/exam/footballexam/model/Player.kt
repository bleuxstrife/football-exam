package com.dicoding.exam.footballexam.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Player(
        @SerializedName("idPlayer")
        var idPlayer: String? =null,

        @SerializedName("strPlayer")
        var playerName: String?=null,

        @SerializedName("strHeight")
        var playerHeight: String?=null,

        @SerializedName("strWeight")
        var playerWeight: String?=null,

        @SerializedName("strPosition")
        var playerPosition: String?=null,

        @SerializedName("strDescriptionEN")
        var playerDescription: String?=null,

        @SerializedName("strCutout")
        var playerIcon: String?=null,

        @SerializedName("strFanart1")
        var playerBanner: String?=null
) : Serializable