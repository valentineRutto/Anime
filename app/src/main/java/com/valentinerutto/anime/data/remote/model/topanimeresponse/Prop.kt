package com.valentinerutto.anime.data.remote.model.topanimeresponse


import com.google.gson.annotations.SerializedName

data class Prop(
    @SerializedName("from")
    val from: From?,
    @SerializedName("to")
    val to: To?
)