package com.valentinerutto.anime.data

import com.skydoves.retrofit.adapters.paging.PagingMapper
import com.valentinerutto.anime.data.remote.model.topanimeresponse.AnimeResponse
import com.valentinerutto.anime.data.remote.model.topanimeresponse.Data

class PagedMapper : PagingMapper<AnimeResponse, Data>{

    override fun map(value: AnimeResponse): List<Data> {
        return value.data!!
    }
//    .map {
//        Anime(id= it.malId, title = it.title, episodes = it.episodes, imgUrl = it.images.jpg.imageUrl, score = it.score, ratings = it.rating, year = it.year.toString(), duration = it.duration)
//    }
}