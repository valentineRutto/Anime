package com.valentinerutto.anime.network.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopAnimeResponse(
    @SerialName("data")
    val `data`: List<Data>,
    @SerialName("pagination")
    val pagination: Pagination
)