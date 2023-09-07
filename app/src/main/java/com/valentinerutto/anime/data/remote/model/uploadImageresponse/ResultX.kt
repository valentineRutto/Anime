package com.valentinerutto.anime.data.remote.model.uploadImageresponse


import com.google.gson.annotations.SerializedName

data class ResultX(
    @SerializedName("anilist")
    val anilist: Int?,
    @SerializedName("episode")
    val episode: Any?,
    @SerializedName("filename")
    val filename: String?,
    @SerializedName("from")
    val from: Double?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("similarity")
    val similarity: Double?,
    @SerializedName("to")
    val to: Double?,
    @SerializedName("video")
    val video: String?
)