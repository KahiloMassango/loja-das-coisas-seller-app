package com.example.seller_app.core.network.di

import com.example.seller_app.core.network.CategoryNetworkDataSource
import com.example.seller_app.core.network.ColorNetworkDataSource
import com.example.seller_app.core.network.GenderNetworkDataSource
import com.example.seller_app.core.network.datasources.ProductRemoteDataSource
import com.example.seller_app.core.network.ProductNetworkDataSource
import com.example.seller_app.core.network.SizeNetworkDataSource
import com.example.seller_app.core.network.datasources.CategoryRemoteDataSource
import com.example.seller_app.core.network.datasources.ColorRemoteDataSource
import com.example.seller_app.core.network.datasources.GenderRemoteDataSource
import com.example.seller_app.core.network.datasources.SizeRemoteDataSource
import com.example.seller_app.core.network.retrofit.RetrofitAppNetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DataSourcesModule {

    @Provides
    fun providesProductNetworkDataSource(
        networkApi: RetrofitAppNetworkApi
    ): ProductRemoteDataSource {
        return ProductNetworkDataSource(networkApi)

    }

    @Provides
    fun providesCategoryRemoteDataSource(
        networkApi: RetrofitAppNetworkApi
    ): CategoryRemoteDataSource = CategoryNetworkDataSource(networkApi)

    @Provides
    fun providesColorRemoteDataSource(
        networkApi: RetrofitAppNetworkApi
    ): ColorRemoteDataSource = ColorNetworkDataSource(networkApi)

    @Provides
    fun providesSizeRemoteDataSource(
        networkApi: RetrofitAppNetworkApi
    ): SizeRemoteDataSource = SizeNetworkDataSource(networkApi)

    @Provides
    fun providesGenderRemoteDataSource(
        networkApi: RetrofitAppNetworkApi
    ): GenderRemoteDataSource = GenderNetworkDataSource(networkApi)
}