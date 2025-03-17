package com.example.seller_app.core.network.di

import com.example.seller_app.core.network.common.AuthenticatedClient
import com.example.seller_app.core.network.common.UnauthenticatedClient
import com.example.seller_app.core.network.retrofit.AppApiService
import com.example.seller_app.core.network.retrofit.PublicApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//const val BASE_URL = "http://10.0.2.2:8080/v1/"
const val BASE_URL = "https://silkworm-immortal-correctly.ngrok-free.app/v1/"

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    fun provideAppApiService(
        @AuthenticatedClient
        okHttpClient: OkHttpClient
    ): AppApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(AppApiService::class.java)
    }

    @Provides
    fun provideAuthApiService(
        @UnauthenticatedClient
        okHttpClient: OkHttpClient
    ): PublicApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(PublicApiService::class.java)
    }
}