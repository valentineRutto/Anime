package com.valentinerutto.anime.network.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Title(
    @SerialName("title")
    val title: String,
    @SerialName("type")
    val type: String
)