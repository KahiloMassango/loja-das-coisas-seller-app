package com.example.seller_app.core.network.di

import com.example.seller_app.core.network.common.AuthenticatedClient
import com.example.seller_app.core.network.common.UnauthenticatedClient
import com.example.seller_app.core.network.interceptors.AccessTokenInterceptor
import com.example.seller_app.core.network.interceptors.AuthenticatorInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object OkhttpModule {

    @AuthenticatedClient
    @Provides
    @Singleton
    fun provideAuthenticatedOkHttpClient(
        accessTokenInterceptor: AccessTokenInterceptor,
        authenticatorInterceptor: AuthenticatorInterceptor
    ): OkHttpClient {
        //val loggingInterceptor = HttpLoggingInterceptor()
        //loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .authenticator(authenticatorInterceptor)
            .addInterceptor(accessTokenInterceptor)
            //.addInterceptor(loggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @UnauthenticatedClient
    @Provides
    @Singleton
    fun providePublicOkHttpClient(): OkHttpClient {
        //val loggingInterceptor = HttpLoggingInterceptor()
        //loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
           // .addInterceptor(loggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }


}