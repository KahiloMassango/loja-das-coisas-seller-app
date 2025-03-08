package com.example.datastore.datasource

import kotlinx.coroutines.flow.Flow

interface JwtLocalDataSource {
    fun isLoggedIn(): Flow<Boolean>
    suspend fun saveAccessToken(token: String)
    suspend fun saveRefreshToken(token: String)
    suspend fun getAccessToken(): String?
    suspend fun getRefreshToken(): String?
    suspend fun clearAllTokens()
}