package com.example.press.model

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class DataStoreManager(context: Context) {
    private val dataStore: DataStore<Preferences> = context.dataStore

    companion object {
        val TOKEN_KEY = stringPreferencesKey("token")
    }

    val authToken: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }

    suspend fun saveAuthToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }
}