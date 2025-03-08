package com.example.seller_app.core.network.datasources

interface JwtNetworkDatasource {
    suspend fun getRefreshToken(refreshToken: String): Result<String>
}