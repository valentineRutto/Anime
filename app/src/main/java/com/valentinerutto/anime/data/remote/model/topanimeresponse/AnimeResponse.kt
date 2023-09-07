package com.valentinerutto.anime.data.remote.model.topanimeresponse


import com.google.gson.annotations.SerializedName

data class AnimeResponse(
    @SerializedName("data")
    val `data`: List<Data>?,
    @SerializedName("pagination")
    val pagination: Pagination?
)