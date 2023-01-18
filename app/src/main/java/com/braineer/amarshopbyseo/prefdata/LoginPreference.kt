package com.braineer.amarshopbyseo.prefdata

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

fun Int.isEven() = this % 2 == 0

private const val prefName = "login_preference"
private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
    name = prefName
)
class LoginPreference(context: Context) {

    private val isLoggedIn = booleanPreferencesKey("isLoggedIn")
    private val userId = longPreferencesKey("user_id")

    val isLoggedInFlow: Flow<Boolean> = context.dataStore.data
        .catch {
            if (it is IOException) {
                emit(emptyPreferences())
            }else {throw it}
        }
        .map {
            it[isLoggedIn] ?: false
        }

    val userIdFlow: Flow<Long> = context.dataStore.data
        .catch {
            if (it is IOException) {
                emit(emptyPreferences())
            }else {throw it}
        }
        .map {
        it[userId] ?: 0
    }
    suspend fun setLoginStatus(status: Boolean, id: Long, context: Context) {
        context.dataStore.edit {
            it[isLoggedIn] = status
            it[userId] = id
        }
    }
}