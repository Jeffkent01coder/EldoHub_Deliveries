package com.example.eldohubdeliveries

class EldoRepository(private val db:EldoDatabase) {

    suspend fun insert(items: Eldoitems) = db.getEldoDao().insert(items)
    suspend fun delete(items: Eldoitems) = db.getEldoDao().delete(items)

    fun getAllItems() = db.getEldoDao().getAllEldoItems()


}