package com.example.menu.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "password")
    var password: String,

    @ColumnInfo(name = "email")
    var email: String,

    var quantity: Int

)