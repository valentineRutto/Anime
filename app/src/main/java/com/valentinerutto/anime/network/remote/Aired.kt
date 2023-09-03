package com.valentinerutto.anime.network.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Aired(
    @SerialName("from")
    val from: String,
    @SerialName("prop")
    val prop: Prop,
    @SerialName("string")
    val string: String,
    @SerialName("to")
    val to: String
)