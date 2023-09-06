package com.valentinerutto.anime.data

import com.valentinerutto.anime.data.local.AnimeEntity
import com.valentinerutto.anime.data.remote.model.TopAnimeResponse

fun map(animeResponse: TopAnimeResponse?): List<AnimeEntity> {
    return animeResponse?.data?.map {
        AnimeEntity(
            id = 0,
            malId = it.malId,
            title = it.title,
            year = it.year.toString(),
            duration = it.duration,
            episodes = it.episodes,
            score = it.score,
            imgUrl = it.images.jpg.imageUrl,
            ratings = it.rating
        )
    } ?: emptyList()
}

