package com.example.seller_app.core.network.interceptors

import com.example.datastore.datasource.JwtLocalDataSource
import com.example.seller_app.core.network.datasources.JwtNetworkDatasource
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthenticatorInterceptor @Inject constructor(
    private val jwtLocalDataSource: JwtLocalDataSource,
    private val jwtNetworkDatasource: JwtNetworkDatasource

) : Authenticator {

    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer"
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        val accessToken = runBlocking { jwtLocalDataSource.getAccessToken() }
        val refreshToken = runBlocking { jwtLocalDataSource.getRefreshToken() } ?: ""
        synchronized(this) {
            val updatedAccessToken = runBlocking { jwtLocalDataSource.getAccessToken() }
            val token = if (updatedAccessToken != accessToken) updatedAccessToken else {
                runBlocking { jwtNetworkDatasource.getRefreshToken(refreshToken) }
                    .getOrNull()?.let { newAccessToken ->
                        runBlocking { jwtLocalDataSource.saveAccessToken(newAccessToken) }
                        newAccessToken
                    }
            }

            if (token == null) {
                runBlocking {
                    jwtLocalDataSource.clearAllTokens()
                }
            }

            return token?.let {
                response.request.newBuilder()
                    .header(HEADER_AUTHORIZATION, "$TOKEN_TYPE $it")
                    .build()
            }
        }
    }

}