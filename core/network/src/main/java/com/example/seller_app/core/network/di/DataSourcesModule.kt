package com.example.seller_app.core.network.di

import com.example.seller_app.core.network.FinanceDataSourceImpl
import com.example.seller_app.core.network.JwtNetworkDataSourceImpl
import com.example.seller_app.core.network.OrderNetworkDataSourceImp
import com.example.seller_app.core.network.ProductNetworkDataSource
import com.example.seller_app.core.network.StoreNetworkDataSourceImpl
import com.example.seller_app.core.network.SyncNetworkDatasourceImpl
import com.example.seller_app.core.network.datasources.FinanceDataSource
import com.example.seller_app.core.network.datasources.JwtNetworkDatasource
import com.example.seller_app.core.network.datasources.OrderNetworkDataSource
import com.example.seller_app.core.network.datasources.ProductRemoteDataSource
import com.example.seller_app.core.network.datasources.StoreNetworkDatasource
import com.example.seller_app.core.network.datasources.SyncNetworkDatasource
import com.example.seller_app.core.network.retrofit.AppApiService
import com.example.seller_app.core.network.retrofit.PublicApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DataSourcesModule {

    @Provides
    fun providesFinanceDataSource(
        appApiService: AppApiService
    ): FinanceDataSource = FinanceDataSourceImpl(appApiService)

    @Provides
    fun providesOrderNetworkDataSource(
        appApiService: AppApiService
    ): OrderNetworkDataSource = OrderNetworkDataSourceImp(appApiService)

    @Provides
    fun providesJwtNetworkDataSource(
        publicApiService: PublicApiService
    ): JwtNetworkDatasource {
        return JwtNetworkDataSourceImpl(publicApiService)

    }

    @Provides
    fun providesStoreNetworkDataSource(
        publicApiService: PublicApiService,
        appApiService: AppApiService
    ): StoreNetworkDatasource {
        return StoreNetworkDataSourceImpl(publicApiService, appApiService)

    }

    @Provides
    fun providesProductNetworkDataSource(
        networkApi: AppApiService
    ): ProductRemoteDataSource {
        return ProductNetworkDataSource(networkApi)

    }

    @Provides
    fun providesSyncNetworkDatasource(
        apiService: PublicApiService
    ): SyncNetworkDatasource = SyncNetworkDatasourceImpl(apiService)
}