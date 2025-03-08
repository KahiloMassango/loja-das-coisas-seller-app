package com.example.seller_app.core.network.retrofit

import com.example.seller_app.core.network.model.Response
import com.example.store.core.network.model.authentication.AuthenticationDtoRes
import com.example.store.core.network.model.authentication.LoginDtoReq
import com.example.store.core.network.model.authentication.RefreshTokenDtoReq
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApiService {

    @POST("auth/login")
    suspend fun login(@Body request: LoginDtoReq): Response<AuthenticationDtoRes>

    @POST("auth/refresh-token")
    suspend fun refreshToken(@Body request: RefreshTokenDtoReq): Response<AuthenticationDtoRes>
}