package com.valentinerutto.anime.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valentinerutto.anime.data.AnimeRepository
import com.valentinerutto.anime.data.local.AnimeEntity
import com.valentinerutto.anime.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val animeRepository: AnimeRepository): ViewModel() {
    private val _sucessAnimeListResponse = MutableLiveData<List<AnimeEntity>?>()
    val successfulAnimeListResponse: LiveData<List<AnimeEntity>?>
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
                _sucessAnimeListResponse.postValue(response.data)
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

}