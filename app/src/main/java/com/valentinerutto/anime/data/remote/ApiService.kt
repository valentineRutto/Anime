package com.valentinerutto.anime.data.remote

import com.skydoves.retrofit.adapters.paging.NetworkPagingSource
import com.skydoves.retrofit.adapters.paging.annotations.PagingKey
import com.skydoves.retrofit.adapters.paging.annotations.PagingKeyConfig
import com.valentinerutto.anime.data.Anime
import com.valentinerutto.anime.data.PagedMapper
import com.valentinerutto.anime.data.remote.model.Data
import com.valentinerutto.anime.data.remote.model.TopAnimeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top/anime")
    @PagingKeyConfig(
        keySize = 25,
        mapper = PagedMapper::class
    )
    suspend fun getTopAnime(
        @Query("limit") limit: Int = 25,
        @PagingKey @Query("page")
        page: Int = 1,
    ): NetworkPagingSource<TopAnimeResponse, Data>
}