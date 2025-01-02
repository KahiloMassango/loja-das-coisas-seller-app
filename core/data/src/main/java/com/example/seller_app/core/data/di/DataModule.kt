package com.example.seller_app.core.data.di

import com.example.seller_app.core.data.CategoryRepository
import com.example.seller_app.core.data.ColorRepository
import com.example.seller_app.core.data.DefaultCategoryRepository
import com.example.seller_app.core.data.DefaultColorRepository
import com.example.seller_app.core.data.DefaultSizeRepository
import com.example.seller_app.core.data.DefaultSubcategoryRepository
import com.example.seller_app.core.data.SizeRepository
import com.example.seller_app.core.data.SubCategoryRepository
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
object DataModule {

    @Provides
    fun provideCategoryRepository(
        categoryDao: CategoryDao
    ): CategoryRepository = DefaultCategoryRepository(categoryDao)

    @Provides
    fun provideSubCategoryRepository(
        subcategoryDao: SubcategoryDao
    ): SubCategoryRepository = DefaultSubcategoryRepository(subcategoryDao)

    @Provides
    fun provideColorRepository(
        colorDao: ColorDao
    ): ColorRepository = DefaultColorRepository(colorDao)

    @Provides
    fun provideSizeRepository(
        sizeDao: SizeDao
    ): SizeRepository = DefaultSizeRepository(sizeDao)

}