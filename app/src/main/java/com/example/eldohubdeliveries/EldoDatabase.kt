package com.example.eldohubdeliveries

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Eldoitems::class], version = 1)
abstract class EldoDatabase : RoomDatabase() {

    abstract fun getEldoDao(): EldoDao

    companion object {
        @Volatile
        private var instance: EldoDatabase? = null
        private var LOCK = Any()

        @InternalCoroutinesApi
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }


        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                EldoDatabase::class.java,
                "Eldo_db"
            ).build()
    }
}