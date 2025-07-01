package com.example.loopie.core.helper

import android.content.SharedPreferences
import androidx.core.content.edit

class PreferenceHelper(private val sharedPreferences: SharedPreferences) {
    private companion object {
        const val IS_LOGGED_IN = "IS_LOGGED_IN"
        const val USER_ID = "USER_ID"
    }

    var isLoggedIn
    get() = sharedPreferences.getBoolean(IS_LOGGED_IN, false)
    set(value) {
        sharedPreferences.edit { putBoolean(IS_LOGGED_IN, value) }
    }

    var userId
    get() = sharedPreferences.getInt(USER_ID, 0)
    set(value) {
        sharedPreferences.edit {putInt(USER_ID, value)}
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

    fun getPrefs(): SharedPreferences = sharedPreferences
}