package com.valentinerutto.anime.data.remote.model.topanimeresponse


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Prop(
    @SerialName("from")
    val from: From,
    @SerialName("to")
    val to: To
)