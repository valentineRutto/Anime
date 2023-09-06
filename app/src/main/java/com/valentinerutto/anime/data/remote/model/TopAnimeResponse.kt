package com.valentinerutto.anime.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopAnimeResponse(
    @SerialName("data")
    val `data`: List<Data>,
    @SerialName("pagination")
    val pagination: Pagination
)