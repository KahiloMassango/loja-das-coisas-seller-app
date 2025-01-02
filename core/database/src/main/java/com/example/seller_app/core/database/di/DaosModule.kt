package com.example.seller_app.core.database.di

import com.example.seller_app.core.database.AppDatabase
import com.example.seller_app.core.database.dao.CategoryDao
import com.example.seller_app.core.database.dao.ColorDao
import com.example.seller_app.core.database.dao.SizeDao
import com.example.seller_app.core.database.dao.SubcategoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesCategoryDao(
        database: AppDatabase): CategoryDao {
        return database.categoryDao()
    }

    @Provides
    fun providesSubcategoryDao(
        database: AppDatabase): SubcategoryDao {
        return database.subcategoryDao()
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