package com.cococue.tebakbuahsayur.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ScoreManager {
    private const val PREF_NAME = "tebak_game_prefs"
    private const val KEY_SCORES = "key_score_history"
    private val gson = Gson()

    fun saveScore(context: Context, record: ScoreRecord) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val currentList = getScores(context).toMutableList()
        currentList.add(0, record) // add to top
        // Keep max 50 records
        val limitedList = if (currentList.size > 50) currentList.subList(0, 50) else currentList
        val json = gson.toJson(limitedList)
        prefs.edit().putString(KEY_SCORES, json).apply()
    }

    fun getScores(context: Context): List<ScoreRecord> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_SCORES, null) ?: return emptyList()
        return try {
            val type = object : TypeToken<List<ScoreRecord>>() {}.type
            gson.fromJson(json, type) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun clearScores(context: Context) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(KEY_SCORES).apply()
    }
}
