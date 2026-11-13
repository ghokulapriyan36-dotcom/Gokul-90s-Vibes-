package com.example.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.l10n.AppLanguage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.rewindDataStore: DataStore<Preferences> by preferencesDataStore(name = "rewind_90s_prefs")

class FavoritesPreferences(private val context: Context) {

    private object Keys {
        val FAVORITE_IDS = stringSetPreferencesKey("favorite_ids")
        val CRT_SCANLINES = booleanPreferencesKey("crt_scanlines_enabled")
        val BEST_TRIVIA_SCORE = intPreferencesKey("best_trivia_score")
        val DARK_MODE = booleanPreferencesKey("crt_dark_mode")
        val APP_LANGUAGE = stringPreferencesKey("app_language")
    }

    val favoriteIds: Flow<Set<String>> = context.rewindDataStore.data.map { prefs ->
        prefs[Keys.FAVORITE_IDS] ?: emptySet()
    }

    val crtScanlinesEnabled: Flow<Boolean> = context.rewindDataStore.data.map { prefs ->
        prefs[Keys.CRT_SCANLINES] ?: true
    }

    val bestTriviaScore: Flow<Int> = context.rewindDataStore.data.map { prefs ->
        prefs[Keys.BEST_TRIVIA_SCORE] ?: 0
    }

    val crtDarkMode: Flow<Boolean> = context.rewindDataStore.data.map { prefs ->
        prefs[Keys.DARK_MODE] ?: false
    }

    val appLanguage: Flow<AppLanguage> = context.rewindDataStore.data.map { prefs ->
        when (prefs[Keys.APP_LANGUAGE]) {
            AppLanguage.ENGLISH.code -> AppLanguage.ENGLISH
            else -> AppLanguage.TAMIL
        }
    }

    suspend fun toggleFavorite(itemId: String) {
        context.rewindDataStore.edit { prefs ->
            val current = prefs[Keys.FAVORITE_IDS] ?: emptySet()
            prefs[Keys.FAVORITE_IDS] = if (itemId in current) {
                current - itemId
            } else {
                current + itemId
            }
        }
    }

    suspend fun setCrtScanlines(enabled: Boolean) {
        context.rewindDataStore.edit { prefs ->
            prefs[Keys.CRT_SCANLINES] = enabled
        }
    }

    suspend fun setCrtDarkMode(enabled: Boolean) {
        context.rewindDataStore.edit { prefs ->
            prefs[Keys.DARK_MODE] = enabled
        }
    }

    suspend fun setAppLanguage(language: AppLanguage) {
        context.rewindDataStore.edit { prefs ->
            prefs[Keys.APP_LANGUAGE] = language.code
        }
    }

    suspend fun updateBestTriviaScore(score: Int) {
        context.rewindDataStore.edit { prefs ->
            val currentBest = prefs[Keys.BEST_TRIVIA_SCORE] ?: 0
            if (score > currentBest) {
                prefs[Keys.BEST_TRIVIA_SCORE] = score
            }
        }
    }
}
