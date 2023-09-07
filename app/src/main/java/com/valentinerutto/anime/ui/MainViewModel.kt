package com.valentinerutto.anime.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.valentinerutto.anime.data.AnimeRepository
import com.valentinerutto.anime.data.UploadedImage
import com.valentinerutto.anime.data.local.AnimeEntity
import com.valentinerutto.anime.data.remote.ApiService
import com.valentinerutto.anime.data.remote.model.topanimeresponse.Data
import com.valentinerutto.anime.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class MainViewModel(private val animeRepository: AnimeRepository, val apiService: ApiService) :
    ViewModel() {
    private val _sucessAnimeListResponse = MutableLiveData<List<AnimeEntity>?>()
    val successfulAnimeListResponse: LiveData<List<AnimeEntity>?>
        get() = _sucessAnimeListResponse

    private val _sucessImageListResponse = MutableLiveData<List<UploadedImage>?>()
    val successfulImageListResponse: LiveData<List<UploadedImage>?>
        get() = _sucessImageListResponse

    private val _errorImageResponse = MutableLiveData<String>()
    val errorImageResponse: LiveData<String>
        get() = _errorImageResponse

    private val _errorAnimeListResponse = MutableLiveData<String>()
    val errorAnimeListResponse: LiveData<String>
        get() = _errorAnimeListResponse

    val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private suspend fun getAnime(page: Int) {
        _isLoading.postValue(true)
        when (val response = animeRepository.getTopAnime(page, 25)) {
            is Resource.Success -> {
                _isLoading.postValue(false)
                _sucessAnimeListResponse.postValue(response.data)
            }

            is Resource.Error -> {
                _isLoading.postValue(false)
                _errorAnimeListResponse.postValue(response.errorMessage)
            }

            else -> {

            }
        }
    }

    private suspend fun getDBAnime() {
        when (val response = animeRepository.getSavedAnime()) {
            is Resource.Success -> {
                _sucessAnimeListResponse.postValue(response.data)
            }

            is Resource.Error -> {
                _errorAnimeListResponse.postValue(response.errorMessage)
            }

            else -> {

            }
        }
    }

    fun getSavedAnime() {
        viewModelScope.launch {
            getDBAnime()
        }
    }

    fun fetchAnimeList(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getAnime(page)
        }
    }

    suspend fun fetchAnimeListAsPagingSource() {
        val animePagedList = animeRepository.fetchAnimeListAsPagingSource()

        animePagedList.collectLatest {

            val animelist = it.map { it ->
//                Anime(
//                    id = it.malId,
//                    title = it.title,
//                    episodes = it.episodes,
//                    imgUrl = it.images.jpg.imageUrl,
//                    score = it.score,
//                    ratings = it.rating,
//                    year = it.year.toString(),
//                    duration = it.duration
//                )
            }
            // _sucessAnimeListResponse.postValue(animelist.)
        }

    }

    suspend fun fetchPagingSource(): Flow<PagingData<Data>> {
        return animeRepository.fetchAnimeListAsPagingSource()
    }

    fun uploadImage(image: MultipartBody.Part) {
        viewModelScope.launch {
            _isLoading.postValue(true)


            when (val result = animeRepository.uploadImage(image)) {
                is Resource.Error -> {
                    _isLoading.postValue(false)
                    _errorImageResponse.postValue(result.errorMessage)
                }

                is Resource.Success -> {
                    _isLoading.postValue(false)
                    _sucessImageListResponse.postValue(result.data)
                }

                else -> {
                    //to be done
                }
            }
        }
    }
}