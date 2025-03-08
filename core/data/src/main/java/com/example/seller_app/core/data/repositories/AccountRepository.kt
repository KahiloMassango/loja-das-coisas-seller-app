package com.example.seller_app.core.data.repositories

import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun logout(): Result<Unit>
    fun isUserLoggedIn(): Flow<Boolean>
}