package com.example.seller_app.core.network

import com.example.seller_app.core.network.datasources.JwtNetworkDatasource
import com.example.seller_app.core.network.retrofit.AuthApiService
import com.example.store.core.network.model.authentication.RefreshTokenDtoReq
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class JwtNetworkDataSourceImpl(
    private val authApiService: AuthApiService,
): JwtNetworkDatasource {
    override suspend fun getRefreshToken(refreshToken: String): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                val response = authApiService.refreshToken(RefreshTokenDtoReq(refreshToken))
                Result.success(response.data.accessToken)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}