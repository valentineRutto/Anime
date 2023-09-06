package com.valentinerutto.anime.data.local

import androidx.room.Dao
import androidx.room.Query
import com.valentinerutto.anime.util.BaseDao

@Dao
interface AnimeDao : BaseDao<AnimeEntity> {

    @Query("SELECT * FROM anime_table")
    fun getTravelDetails(): List<AnimeEntity>

}