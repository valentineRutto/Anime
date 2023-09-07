package com.valentinerutto.anime.data.remote

import com.skydoves.retrofit.adapters.paging.NetworkPagingSource
import com.skydoves.retrofit.adapters.paging.annotations.PagingKey
import com.skydoves.retrofit.adapters.paging.annotations.PagingKeyConfig
import com.valentinerutto.anime.data.PagedMapper
import com.valentinerutto.anime.data.remote.model.topanimeresponse.Data
import com.valentinerutto.anime.data.remote.model.topanimeresponse.TopAnimeResponse
import com.valentinerutto.anime.data.remote.model.uploadImageresponse.SearchImagePostResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
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
    @Multipart
    @POST("https://api.trace.moe/search")
    suspend fun uploadImage(
     @Part image: MultipartBody.Part
    ): Response<SearchImagePostResponse>
}