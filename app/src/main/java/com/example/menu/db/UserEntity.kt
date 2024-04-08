package com.example.menu.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "avatar")
    var avatar: String?,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "polDB")
    var polDB: String?,

    @ColumnInfo(name = "ageDB")
    var ageDB: Int?,

    @ColumnInfo(name = "sizeDB")
    var sizeDB: Int?,

    @ColumnInfo(name = "password")
    var password: String,

    var quantity: Int

)