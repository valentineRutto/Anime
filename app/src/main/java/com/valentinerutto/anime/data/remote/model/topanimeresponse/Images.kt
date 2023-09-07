package com.valentinerutto.anime.data.remote.model.topanimeresponse


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Images(
    @SerialName("jpg")
    val jpg: Jpg,
    @SerialName("webp")
    val webp: Webp
)