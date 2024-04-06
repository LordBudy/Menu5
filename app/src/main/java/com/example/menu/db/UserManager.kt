package com.example.menu.db

import android.content.Context
import android.content.SharedPreferences

object UserManager {
    private const val PREF_NAME = "user_prefs"
    private const val KEY_IS_REGISTERED = "is_registered"

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun isRegistered(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_REGISTERED, false)
    }

    fun setRegistered(isRegistered: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_IS_REGISTERED, isRegistered).apply()
    }
}
