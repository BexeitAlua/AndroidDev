package com.example.assignment2

import android.content.Context

object LikesStorage {
    private const val PREF_NAME = "likes_prefs"
    private const val KEY_LIKES = "liked_urls"

    fun saveLikes(context: Context, liked: Set<String>) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putStringSet(KEY_LIKES, liked).apply()
    }

    fun loadLikes(context: Context): MutableSet<String> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getStringSet(KEY_LIKES, emptySet())?.toMutableSet() ?: mutableSetOf()
    }
}
