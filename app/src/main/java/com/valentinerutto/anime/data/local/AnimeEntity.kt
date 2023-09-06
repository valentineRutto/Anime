package com.valentinerutto.anime.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_table")
data class AnimeEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val malId:Int?,
    val title: String?,
    val episodes: Int?,
    val imgUrl: String?,
    val ratings: String?,
    val score: Double?,
    val year: String?,
    val duration: String?
)