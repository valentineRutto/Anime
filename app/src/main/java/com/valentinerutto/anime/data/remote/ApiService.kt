package com.valentinerutto.anime.data.remote

import com.valentinerutto.anime.data.remote.model.TopAnimeResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("top/anime")
    suspend fun getTopAnime(): Response<TopAnimeResponse>
}