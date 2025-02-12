package com.example.seller_app.core.data.di

import com.example.seller_app.core.data.GenderRepository
import com.example.seller_app.core.data.ColorRepository
import com.example.seller_app.core.data.DefaultGenderRepository
import com.example.seller_app.core.data.DefaultColorRepository
import com.example.seller_app.core.data.DefaultSizeRepository
import com.example.seller_app.core.data.DefaultCategoryRepository
import com.example.seller_app.core.data.SizeRepository
import com.example.seller_app.core.data.CategoryRepository
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
object DataModule {

    @Provides
    fun provideGenderRepository(
        genderDao: GenderDao
    ): GenderRepository = DefaultGenderRepository(genderDao)

    @Provides
    fun provideSubCategoryRepository(
        categoryDao: CategoryDao
    ): CategoryRepository = DefaultCategoryRepository(categoryDao)

    @Provides
    fun provideColorRepository(
        colorDao: ColorDao
    ): ColorRepository = DefaultColorRepository(colorDao)

    @Provides
    fun provideSizeRepository(
        sizeDao: SizeDao
    ): SizeRepository = DefaultSizeRepository(sizeDao)

}