package com.valentinerutto.anime.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.valentinerutto.anime.util.Constants

@Database(entities = [AnimeEntity::class], version = 1,exportSchema = false)
abstract class AnimeDatabase:RoomDatabase() {
    abstract fun animeDao(): AnimeDao

    companion object {
        @Volatile
        private var INSTANCE: AnimeDatabase? = null
        fun getDatabase(context: Context): AnimeDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, AnimeDatabase::class.java, Constants.DB_NAME
                )
                    // Wipes and rebuilds instead of migrating if no M¬igration object.
                    .fallbackToDestructiveMigration().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}