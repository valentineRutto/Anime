package com.valentinerutto.anime.data

import com.valentinerutto.anime.data.local.AnimeDao
import com.valentinerutto.anime.data.local.AnimeEntity
import com.valentinerutto.anime.data.remote.ApiService
import com.valentinerutto.anime.util.Resource

class AnimeRepository(private val apiservice: ApiService, private val animeDao: AnimeDao) {

    suspend fun getTopAnime(): Resource<List<AnimeEntity>> {
        val response = apiservice.getTopAnime()
        if (!response.isSuccessful)return Resource.Error(errorMessage = response.message())
        val entity = mapResponseToEntity(response.body())
        animeDao.insert(entity)
        return Resource.Success(data = animeDao.getTravelDetails())
    }
}