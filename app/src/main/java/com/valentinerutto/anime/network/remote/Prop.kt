package com.valentinerutto.anime.network.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Prop(
    @SerialName("from")
    val from: From,
    @SerialName("to")
    val to: To
)