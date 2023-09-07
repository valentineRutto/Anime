package com.valentinerutto.anime.data.remote.model.topanimeresponse


import com.google.gson.annotations.SerializedName

data class From(
    @SerializedName("day")
    val day: Int?,
    @SerializedName("month")
    val month: Int?,
    @SerializedName("year")
    val year: Int?
)