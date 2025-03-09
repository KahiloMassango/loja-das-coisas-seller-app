package com.example.seller_app.core.network.interceptors

import com.example.datastore.datasource.JwtLocalDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class AccessTokenInterceptor @Inject constructor(
    private val jwtLocalDataSource: JwtLocalDataSource
): Interceptor {

    companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
        const val TOKEN_TYPE = "bearer"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = runBlocking { jwtLocalDataSource.getAccessToken() }
        val request = chain.request().newBuilder()
        request.addHeader(AUTHORIZATION_HEADER, "$TOKEN_TYPE $accessToken")

        return chain.proceed(request.build())
    }

}