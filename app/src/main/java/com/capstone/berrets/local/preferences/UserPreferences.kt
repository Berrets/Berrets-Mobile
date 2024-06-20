package com.capstone.berrets.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.capstone.berrets.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

class UserPreferences private constructor(private val dataStore: DataStore<Preferences>) {
	suspend fun saveSession(user: User) {
		dataStore.edit { preferences ->
			preferences[EMAIL_KEY] = user.email
			preferences[TOKEN_KEY] = user.token
			preferences[IS_LOGIN_KEY] = true
		}
	}

	fun getSession(): Flow<User> {
		return dataStore.data.map { preferences ->
			User(
				preferences[EMAIL_KEY] ?: "",
				preferences[TOKEN_KEY] ?: "",
				preferences[IS_LOGIN_KEY] ?: false
			)
		}
	}

	suspend fun logout() {
		dataStore.edit { preferences ->
			preferences.clear()
		}
	}

	suspend fun saveAuthProvider(authProvider: String) {
		dataStore.edit { preferences ->
			preferences[AUTH_PROVIDER_KEY] = authProvider
		}
	}

	suspend fun getAuthProvider(): String {
		return dataStore.data.map { preferences ->
			preferences[AUTH_PROVIDER_KEY] ?: ""
		}.first()
	}

	companion object {
		@Volatile
		private var INSTANCE: UserPreferences? = null

		private var EMAIL_KEY = stringPreferencesKey("email")
		private var TOKEN_KEY = stringPreferencesKey("token")
		private var IS_LOGIN_KEY = booleanPreferencesKey("isLogin")

		private val AUTH_PROVIDER_KEY = stringPreferencesKey("authProvider")

		fun getInstance(dataStore: DataStore<Preferences>): UserPreferences {
			return INSTANCE ?: synchronized(this) {
				val instance = UserPreferences(dataStore)
				INSTANCE = instance
				instance
			}
		}
	}
}