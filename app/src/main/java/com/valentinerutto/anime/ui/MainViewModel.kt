package com.valentinerutto.anime.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.valentinerutto.anime.data.Anime
import com.valentinerutto.anime.data.AnimeRepository
import com.valentinerutto.anime.data.remote.ApiService
import com.valentinerutto.anime.data.remote.model.Data
import com.valentinerutto.anime.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel(private val animeRepository: AnimeRepository, val apiService: ApiService) :
    ViewModel() {
    private val _sucessAnimeListResponse = MutableLiveData<List<Anime>?>()
    val successfulAnimeListResponse: LiveData<List<Anime>?>
        get() = _sucessAnimeListResponse

    private val _errorAnimeListResponse = MutableLiveData<String>()
    val errorAnimeListResponse: LiveData<String>
        get() = _errorAnimeListResponse

    val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private suspend fun getAnime() {
        _isLoading.postValue(true)
        when (val response = animeRepository.getTopAnime()) {
            is Resource.Success -> {
                _isLoading.postValue(false)
                //  _sucessAnimeListResponse.postValue(response.data)
            }

            is Resource.Error -> {
                _isLoading.postValue(false)
                _errorAnimeListResponse.postValue(response.errorMessage)
            }
        }
    }

    fun fetchAnimeList() {
        viewModelScope.launch(Dispatchers.IO) {
            getAnime()
        }
    }

    suspend fun fetchAnimeListAsPagingSource() {

        val animePagedList = animeRepository.fetchAnimeListAsPagingSource()

        animePagedList.collectLatest {

            val animelist = it.map { it ->
                Anime(
                    id = it.malId,
                    title = it.title,
                    episodes = it.episodes,
                    imgUrl = it.images.jpg.imageUrl,
                    score = it.score,
                    ratings = it.rating,
                    year = it.year.toString(),
                    duration = it.duration
                )
            }
           // _sucessAnimeListResponse.postValue(animelist.)
        }

    }

    suspend fun fetchPagingSource(): Flow<PagingData<Data>> = flow {
        val pagingSource = apiService.getTopAnime()
                val pagerFlow = Pager(PagingConfig(pageSize = 20)) { pagingSource }.flow
        emitAll(pagerFlow)
    }
}