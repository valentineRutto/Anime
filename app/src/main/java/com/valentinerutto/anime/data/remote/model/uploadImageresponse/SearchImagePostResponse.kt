package com.valentinerutto.anime.data.remote.model.uploadImageresponse


import com.google.gson.annotations.SerializedName

data class SearchImagePostResponse(
    @SerializedName("error")
    val error: String?,
    @SerializedName("frameCount")
    val frameCount: Int?,
    @SerializedName("result")
    val result: List<ResultX>?
)