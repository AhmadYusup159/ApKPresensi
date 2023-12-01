package com.example.press.model

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class DataStoreManager(context: Context) {
    private val dataStore: DataStore<Preferences> = context.dataStore

    companion object {
        val TOKEN_KEY = stringPreferencesKey("token")
        val ID_USER_KEY = stringPreferencesKey("id_user")
    }

    val authToken: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }

    val userid: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[ID_USER_KEY]
        }

    suspend fun getUserId(): Int? {
        val preferences = dataStore.data.first()
        return preferences[ID_USER_KEY]?.toIntOrNull()
    }

    suspend fun saveUserId(id: String) {
        dataStore.edit { preferences ->
            preferences[ID_USER_KEY] = id
        }
    }

    suspend fun saveAuthToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    suspend fun isAuthTokenAvailable(): Boolean {
        val preferences = dataStore.data.first()
        return preferences[TOKEN_KEY] != null
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }
}
