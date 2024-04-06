package com.example.menu.db

import android.content.Context
import android.content.SharedPreferences
//объект UserManager,предоставляет статические методы для управления информацией о регистрации
// пользователя в приложении с использованием SharedPreferences
object UserManager {
    //PREF_NAME константа, представляющая имя файла SharedPreferences,
    // в котором будет храниться информация о регистрации пользователя.
    private const val PREF_NAME = "user_prefs"
    //KEY_IS_REGISTERED ключ, с помощью которого будет извлекаться
    // информация о том, зарегистрирован ли пользователь.
    private const val KEY_IS_REGISTERED = "is_registered"

    private lateinit var sharedPreferences: SharedPreferences
//init(context: Context) метод для инициализации UserManager. Он принимает
//контекст приложения и использует его для получения экземпляра SharedPreferences, связанного с файлом PREF_NAME
    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }
//isRegistered()метод для проверки, зарегистрирован ли пользователь.
    fun isRegistered(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_REGISTERED, false)
    }
//setRegistered(isRegistered: Boolean) метод для установки статуса регистрации пользователя
    fun setRegistered(isRegistered: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_IS_REGISTERED, isRegistered).apply()
    }
}
