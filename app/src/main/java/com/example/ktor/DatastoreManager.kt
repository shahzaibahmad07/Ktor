package com.example.ktor

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatastoreManager(private val context: Context) {
    private val testString = stringPreferencesKey("Test String")

    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "data")

    suspend fun addValue(value: String) {
        context.datastore.edit {
            it[testString] = value
        }
    }

    val getValue: Flow<String> = context.datastore.data.map {
        it[testString] ?: ""
    }
}