package com.example.menu.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (
    entities = [DishEntity::class,UserEntity::class],

    version = 1,
    exportSchema = false
)
abstract class MainDb: RoomDatabase() {

//вызываем интерфейс ImageDao с функциями
abstract fun getDao(): Dao


    //функция создает базу данных или вызывает уже сущестыующую инстанцию
    companion object {
        @Volatile
        private var INSTANCE: MainDb? = null

        fun getDb(context: Context): MainDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDb::class.java,
                    "main_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}
