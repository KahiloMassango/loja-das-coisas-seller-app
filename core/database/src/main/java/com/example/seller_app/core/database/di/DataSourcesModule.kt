package com.example.seller_app.core.database.di

import com.example.seller_app.core.database.CategoryLocalDataSourceImp
import com.example.seller_app.core.database.ColorLocalDataSourceImp
import com.example.seller_app.core.database.GenderLocalDataSourceImpl
import com.example.seller_app.core.database.SizeLocalDataSourceImpl
import com.example.seller_app.core.database.dao.CategoryDao
import com.example.seller_app.core.database.dao.ColorDao
import com.example.seller_app.core.database.dao.GenderDao
import com.example.seller_app.core.database.dao.SizeDao
import com.example.seller_app.core.database.datasources.CategoryLocalDataSource
import com.example.seller_app.core.database.datasources.ColorLocalDataSource
import com.example.seller_app.core.database.datasources.GenderLocalDataSource
import com.example.seller_app.core.database.datasources.SizeLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourcesModule {

    @Provides
    fun providesCategoryLocalDataSource(
        categoryDao: CategoryDao
    ): CategoryLocalDataSource = CategoryLocalDataSourceImp(categoryDao)

    @Provides
    fun providesColorLocalDataSource(
        colorDao: ColorDao
    ): ColorLocalDataSource = ColorLocalDataSourceImp(colorDao)

    @Provides
    fun providesSizeLocalDataSource(
        sizeDao: SizeDao
    ): SizeLocalDataSource = SizeLocalDataSourceImpl(sizeDao)

    @Provides
    fun provideGenderLocalDataSource(
        genderDao: GenderDao
    ): GenderLocalDataSource = GenderLocalDataSourceImpl(genderDao)


}