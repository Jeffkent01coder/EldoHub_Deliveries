package com.example.eldohubdeliveries

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EldoItems")
data class Eldoitems (

    @ColumnInfo(name = "itemName")
    var itemName: String,

    @ColumnInfo(name = "itemQuantity")
    var itemQuantity: Int,

    @ColumnInfo(name = "itemPrice")
    var itemPrice: Int,
)
{
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null
}