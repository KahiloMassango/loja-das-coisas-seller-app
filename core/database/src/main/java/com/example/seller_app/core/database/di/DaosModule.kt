package com.example.seller_app.core.database.di

import com.example.seller_app.core.database.db.AppDatabase
import com.example.seller_app.core.database.dao.GenderDao
import com.example.seller_app.core.database.dao.ColorDao
import com.example.seller_app.core.database.dao.SizeDao
import com.example.seller_app.core.database.dao.CategoryDao
import com.example.seller_app.core.database.dao.GenderCategoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {

    @Provides
    fun providesGenderCategoryDao(
        database: AppDatabase
    ): GenderCategoryDao = database.genderCategoryDao()

    @Provides
    fun providesGenderDao(
        database: AppDatabase
    ): GenderDao {
        return database.genderDao()
    }

    @Provides
    fun providesCategoryDao(
        database: AppDatabase
    ): CategoryDao {
        return database.categoryDao()
    }

    @Provides
    fun providesColorDao(
        database: AppDatabase
    ): ColorDao {
        return database.colorDao()
    }

    @Provides
    fun providesSizeDao(
        database: AppDatabase
    ): SizeDao {
        return database.sizeDao()
    }

}