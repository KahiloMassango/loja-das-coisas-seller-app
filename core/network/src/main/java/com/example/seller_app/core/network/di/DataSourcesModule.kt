package com.example.seller_app.core.network.di

import com.example.seller_app.core.network.CategoryNetworkDataSource
import com.example.seller_app.core.network.ColorNetworkDataSource
import com.example.seller_app.core.network.GenderNetworkDataSource
import com.example.seller_app.core.network.JwtNetworkDataSourceImpl
import com.example.seller_app.core.network.datasources.ProductRemoteDataSource
import com.example.seller_app.core.network.ProductNetworkDataSource
import com.example.seller_app.core.network.SizeNetworkDataSource
import com.example.seller_app.core.network.StoreNetworkDataSourceImpl
import com.example.seller_app.core.network.datasources.CategoryRemoteDataSource
import com.example.seller_app.core.network.datasources.ColorRemoteDataSource
import com.example.seller_app.core.network.datasources.GenderRemoteDataSource
import com.example.seller_app.core.network.datasources.JwtNetworkDatasource
import com.example.seller_app.core.network.datasources.SizeRemoteDataSource
import com.example.seller_app.core.network.datasources.StoreNetworkDatasource
import com.example.seller_app.core.network.retrofit.AppApiService
import com.example.seller_app.core.network.retrofit.AuthApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DataSourcesModule {

    @Provides
    fun providesJwtNetworkDatasource(
        authApiService: AuthApiService
    ): JwtNetworkDatasource {
        return JwtNetworkDataSourceImpl(authApiService)

    }

    @Provides
    fun providesStoreNetworkDatasource(
        authApiService: AuthApiService,
        appApiService: AppApiService
    ): StoreNetworkDatasource {
        return StoreNetworkDataSourceImpl(authApiService, appApiService)

    }

    @Provides
    fun providesProductNetworkDataSource(
        networkApi: AppApiService
    ): ProductRemoteDataSource {
        return ProductNetworkDataSource(networkApi)

    }

    @Provides
    fun providesCategoryRemoteDataSource(
        networkApi: AppApiService
    ): CategoryRemoteDataSource = CategoryNetworkDataSource(networkApi)

    @Provides
    fun providesColorRemoteDataSource(
        networkApi: AppApiService
    ): ColorRemoteDataSource = ColorNetworkDataSource(networkApi)

    @Provides
    fun providesSizeRemoteDataSource(
        networkApi: AppApiService
    ): SizeRemoteDataSource = SizeNetworkDataSource(networkApi)

    @Provides
    fun providesGenderRemoteDataSource(
        networkApi: AppApiService
    ): GenderRemoteDataSource = GenderNetworkDataSource(networkApi)
}