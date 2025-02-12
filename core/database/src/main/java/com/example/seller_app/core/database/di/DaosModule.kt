package com.example.seller_app.core.database.di

import com.example.seller_app.core.database.AppDatabase
import com.example.seller_app.core.database.dao.GenderDao
import com.example.seller_app.core.database.dao.ColorDao
import com.example.seller_app.core.database.dao.SizeDao
import com.example.seller_app.core.database.dao.CategoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesGenderDao(
        database: AppDatabase): GenderDao {
        return database.genderDao()
    }

    @Provides
    fun providesCategoryDao(
        database: AppDatabase): CategoryDao {
        return database.categoryDao()
    }

    @Provides
    fun providesColorDao(
        database: AppDatabase): ColorDao {
        return database.colorDao()
    }

    @Provides
    fun providesSizeDao(
        database: AppDatabase): SizeDao {
        return database.sizeDao()
    }

}