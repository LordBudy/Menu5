package com.example.menu.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ImageDao {
    //для записи используем анотацию @Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDish(dish: DishEntity)

    //для считывания информации из db используем анотацию @Query
    @Query("SELECT * FROM dishs")//стандартный запрос для sqlite значит (выбрать все колонки из ентити dish)
   fun getAllDish():List<DishEntity>

    // метод для удаления объекта из таблицы
    @Delete
    suspend fun deleteDish(dish: DishEntity)

    // метод для обновления объекта в таблице
    @Update
    suspend fun updateDish(dish: DishEntity)
//проверка по id если есть такой элемент то не добавляем а обновляем данные
    @Query("SELECT * FROM dishs WHERE id_dish = :id")
    suspend fun getDishById(id: Int?): DishEntity?
}