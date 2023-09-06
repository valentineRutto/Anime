package com.valentinerutto.anime.di

import com.valentinerutto.anime.App
import com.valentinerutto.anime.data.AnimeRepository
import com.valentinerutto.anime.data.local.AnimeDatabase
import com.valentinerutto.anime.data.remote.ApiService
import com.valentinerutto.anime.ui.MainViewModel
import com.valentinerutto.anime.util.Constants
import com.valentinerutto.anime.util.RetrofitClient.createOkClient
import com.valentinerutto.anime.util.RetrofitClient.createRetrofit
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {

    single { App.INSTANCE }
    single<ApiService> { (get() as Retrofit).create(ApiService::class.java) }
    single { createOkClient() }

    single {
        createRetrofit(baseUrl = Constants.BASE_URL, get())
    }
    single { AnimeRepository(apiservice = get(),animeDao = database().animeDao()) }

    viewModel { MainViewModel(get()) }

    single { AnimeDatabase.getDatabase(context = androidContext()) }

}

fun Scope.database() = get<AnimeDatabase>()