package com.example.seller_app.core.network.di

import com.example.seller_app.core.network.retrofit.RetrofitAppNetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://10.0.2.2:8080/v1/store/"

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    fun providesAppNetworkApi(): RetrofitAppNetworkApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitAppNetworkApi::class.java)
    }
}