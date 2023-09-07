package com.valentinerutto.anime.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.valentinerutto.anime.data.local.AnimeDao
import com.valentinerutto.anime.data.local.AnimeEntity
import com.valentinerutto.anime.data.remote.ApiService
import com.valentinerutto.anime.data.remote.model.topanimeresponse.Data
import com.valentinerutto.anime.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody

class AnimeRepository(private val apiservice: ApiService, private val animeDao: AnimeDao) {

    suspend fun getTopAnime(): Resource<List<AnimeEntity>> {
        val response = apiservice.getTopAnime()
//        if (!response.isSuccessful)return Resource.Error(errorMessage = response.message())
//        val entity = mapResponseToEntity(response.body())
        // animeDao.insert(entity)
        return Resource.Success(data = animeDao.getTravelDetails())
    }

    suspend fun uploadImage(image: MultipartBody.Part):Resource<List<UploadedImage>>{
        val response = apiservice.uploadImage(image)
        if (!response.isSuccessful) return Resource.Error(errorMessage = response.message())
        val  uploadedImage = mapImage(response.body())
        return Resource.Success(data = uploadedImage)
    }

    suspend fun fetchAnimeListAsPagingSource(): Flow<PagingData<Data>> = flow {
        val pagingSource = apiservice.getTopAnime()
        val pagerFlow = Pager(PagingConfig(pageSize = 25)) { pagingSource }.flow
        emitAll(pagerFlow)
    }
}