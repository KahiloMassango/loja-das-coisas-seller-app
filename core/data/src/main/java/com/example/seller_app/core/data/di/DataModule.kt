package com.example.seller_app.core.data.di

import android.content.Context
import androidx.work.WorkManager
import com.example.datastore.PreferencesDataSource
import com.example.datastore.datasource.JwtLocalDataSource
import com.example.seller_app.core.data.AccountRepositoryImpl
import com.example.seller_app.core.data.CategoryRepositoryImpl
import com.example.seller_app.core.data.ColorRepositoryImpl
import com.example.seller_app.core.data.FinanceRepositoryImpl
import com.example.seller_app.core.data.GenderRepositoryImpl
import com.example.seller_app.core.data.OrderRepositoryImpl
import com.example.seller_app.core.data.PreferenceRepositoryImpl
import com.example.seller_app.core.data.ProductRepositoryImpl
import com.example.seller_app.core.data.SizeRepositoryImpl
import com.example.seller_app.core.data.SyncManager
import com.example.seller_app.core.data.SyncRepositoryImpl
import com.example.seller_app.core.data.repositories.AccountRepository
import com.example.seller_app.core.data.repositories.CategoryRepository
import com.example.seller_app.core.data.repositories.ColorRepository
import com.example.seller_app.core.data.repositories.FinanceRepository
import com.example.seller_app.core.data.repositories.GenderRepository
import com.example.seller_app.core.data.repositories.OrderRepository
import com.example.seller_app.core.data.repositories.PreferenceRepository
import com.example.seller_app.core.data.repositories.ProductRepository
import com.example.seller_app.core.data.repositories.SizeRepository
import com.example.seller_app.core.data.repositories.SyncRepository
import com.example.seller_app.core.database.datasources.CategoryLocalDataSource
import com.example.seller_app.core.database.datasources.ColorLocalDataSource
import com.example.seller_app.core.database.datasources.GenderLocalDataSource
import com.example.seller_app.core.database.datasources.SizeLocalDataSource
import com.example.seller_app.core.network.datasources.FinanceDataSource
import com.example.seller_app.core.network.datasources.OrderNetworkDataSource
import com.example.seller_app.core.network.datasources.ProductRemoteDataSource
import com.example.seller_app.core.network.datasources.StoreNetworkDatasource
import com.example.seller_app.core.network.datasources.SyncNetworkDatasource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun providesSyncRepository(
        syncNetworkDatasource: SyncNetworkDatasource
    ): SyncRepository = SyncRepositoryImpl(syncNetworkDatasource)

    @Provides
    fun providesFinanceRepository(
        financeDataSource: FinanceDataSource
    ): FinanceRepository = FinanceRepositoryImpl(financeDataSource)

    @Provides
    fun providesSyncManager(
        workManager: WorkManager,
        syncNetworkDatasource: SyncNetworkDatasource
    ): SyncManager = SyncManager(workManager, syncNetworkDatasource)

    @Provides
    fun providesOrderRepository(
        orderNetworkDataSource: OrderNetworkDataSource
    ): OrderRepository = OrderRepositoryImpl(orderNetworkDataSource)

    @Provides
    fun providesAccountRepository(
        storeNetworkDatasource: StoreNetworkDatasource,
        jwtLocalDataSource: JwtLocalDataSource
    ): AccountRepository = AccountRepositoryImpl(storeNetworkDatasource, jwtLocalDataSource)

    @Provides
    fun providesPreferencesRepository(
        preferenceDatasource: PreferencesDataSource
    ): PreferenceRepository = PreferenceRepositoryImpl(preferenceDatasource)

    @Provides
    fun provideGenderRepository(
        localDataSource: GenderLocalDataSource,
    ): GenderRepository = GenderRepositoryImpl(localDataSource)

    @Provides
    fun provideCategoryRepository(
        localDataSource: CategoryLocalDataSource,
    ): CategoryRepository = CategoryRepositoryImpl(localDataSource)

    @Provides
    fun provideColorRepository(
        localDataSource: ColorLocalDataSource,
    ): ColorRepository = ColorRepositoryImpl(localDataSource)

    @Provides
    fun provideSizeRepository(
        localDataSource: SizeLocalDataSource,
    ): SizeRepository = SizeRepositoryImpl(localDataSource)

    @Provides
    fun provideProductRepository(
        productRemoteDataSource: ProductRemoteDataSource,
        @ApplicationContext
        context: Context
    ): ProductRepository {
        return ProductRepositoryImpl(productRemoteDataSource, context)
    }
}