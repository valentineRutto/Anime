package com.valentinerutto.anime.data

import com.skydoves.retrofit.adapters.paging.PagingMapper
import com.valentinerutto.anime.data.remote.model.topanimeresponse.Data
import com.valentinerutto.anime.data.remote.model.topanimeresponse.TopAnimeResponse

class PagedMapper : PagingMapper<TopAnimeResponse, Data>{

    override fun map(value: TopAnimeResponse): List<Data> {
        return value.data
    }
//    .map {
//        Anime(id= it.malId, title = it.title, episodes = it.episodes, imgUrl = it.images.jpg.imageUrl, score = it.score, ratings = it.rating, year = it.year.toString(), duration = it.duration)
//    }
}