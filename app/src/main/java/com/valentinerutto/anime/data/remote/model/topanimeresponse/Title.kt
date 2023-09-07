package com.valentinerutto.anime.data.remote.model.topanimeresponse


import com.google.gson.annotations.SerializedName

data class Title(
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?
)