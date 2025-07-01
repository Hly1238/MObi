package com.example.loopie.core.di

import android.content.Context
import com.example.loopie.core.helper.PreferenceHelper

object ServiceLocator {
    private const val PREFS_NAME = "MyAppPrefs"
    private var preferenceHelper: PreferenceHelper? = null

    fun init(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        preferenceHelper = PreferenceHelper(sharedPreferences)
    }

    fun getPreferenceHelper(): PreferenceHelper {
        return preferenceHelper ?: throw IllegalStateException("ServiceLocator must be initialized with a Context first")
    }
}