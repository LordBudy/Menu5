package com.example.menu.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "dishs")
data class DishEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "url")
    var url_dish: String,

    @ColumnInfo(name = "name")
    var name_dish: String,

    @ColumnInfo(name = "price")
    var price_dish: String,

    @ColumnInfo(name = "countPrice")
    var countPrice: Double,

    @ColumnInfo(name = "weight")
    var weight_dish: String,

    var quantity: Int

)
