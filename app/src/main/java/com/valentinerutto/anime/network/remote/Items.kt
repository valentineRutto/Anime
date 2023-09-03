package com.valentinerutto.anime.network.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Items(
    @SerialName("count")
    val count: Int,
    @SerialName("per_page")
    val perPage: Int,
    @SerialName("total")
    val total: Int
)