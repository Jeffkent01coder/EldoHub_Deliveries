package com.example.eldohubdeliveries

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EldoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Eldoitems)

    @Delete
    suspend fun delete(item: Eldoitems)

    @Query("SELECT * FROM Eldoitems")
    fun getAllEldoItems(): LiveData<List<Eldoitems>>
}