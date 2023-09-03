package com.valentinerutto.anime.network.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Licensor(
    @SerialName("mal_id")
    val malId: Int,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: String,
    @SerialName("url")
    val url: String
)