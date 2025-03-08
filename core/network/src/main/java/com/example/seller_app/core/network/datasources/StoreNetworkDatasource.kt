package com.example.seller_app.core.network.datasources

import com.example.store.core.network.model.authentication.AuthenticationDtoRes

interface StoreNetworkDatasource {
    suspend fun getStoreDetails()

    suspend fun login(identifier: String, password: String): Result<AuthenticationDtoRes>

    suspend fun logout(): Result<Unit>
}