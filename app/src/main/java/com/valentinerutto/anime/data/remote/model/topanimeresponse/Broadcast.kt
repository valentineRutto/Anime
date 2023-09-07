package com.valentinerutto.anime.data.remote.model.topanimeresponse


import com.google.gson.annotations.SerializedName

data class Broadcast(
    @SerializedName("day")
    val day: String?,
    @SerializedName("string")
    val string: String?,
    @SerializedName("time")
    val time: String?,
    @SerializedName("timezone")
    val timezone: String?
)