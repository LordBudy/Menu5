package com.example.menu.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface Dao {
    //для записи блюда используем анотацию @Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDish(dish: DishEntity)

    //для записи нового аккаунта используем анотацию @Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)

    //для считывания информации о блюдах из db используем анотацию @Query
    @Query("SELECT * FROM dishs")//стандартный запрос для sqlite значит (выбрать все колонки из ентити dish)
   fun getAllDish():List<DishEntity>

    //для считывания информации об аккаунтах из db используем анотацию @Query
    @Query("SELECT * FROM users")//стандартный запрос для sqlite значит (выбрать все колонки из ентити users)
    fun getAllUser():List<UserEntity>

    // метод для удаления объекта из таблицы dishs
    @Delete
    suspend fun deleteDish(dish: DishEntity)

    // метод для удаления объекта из таблицы users
    @Delete
    suspend fun deleteUser(user: UserEntity)

    // метод для обновления объекта в таблице dishs
    @Update
    suspend fun updateDish(dish: DishEntity)

    // метод для обновления объекта в таблице user
    @Update
    suspend fun updateUser(user: UserEntity)


//проверка по id если есть такой элемент то не добавляем а обновляем данные
    @Query("SELECT * FROM dishs WHERE id_dish = :id")
    suspend fun getDishById(id: Int?): DishEntity?
}