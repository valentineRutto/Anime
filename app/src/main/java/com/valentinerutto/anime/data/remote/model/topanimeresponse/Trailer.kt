package com.valentinerutto.anime.data.remote.model.topanimeresponse


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Trailer(
    @SerialName("embed_url")
    val embedUrl: String,
    @SerialName("images")
    val images: ImagesX,
    @SerialName("url")
    val url: String,
    @SerialName("youtube_id")
    val youtubeId: String
)