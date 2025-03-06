package com.example.seller_app.core.database.di

import android.content.Context
import com.example.seller_app.core.database.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    fun providesDatabase(
        @ApplicationContext
        context: Context
    ): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

}