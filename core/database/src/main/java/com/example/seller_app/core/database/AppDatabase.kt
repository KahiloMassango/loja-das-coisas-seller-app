package com.example.seller_app.core.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.example.seller_app.core.database.dao.CategoryDao
import com.example.seller_app.core.database.dao.ColorDao
import com.example.seller_app.core.database.dao.SizeDao
import com.example.seller_app.core.database.dao.SubcategoryDao
import com.example.seller_app.core.database.dao.VariationDao
import com.example.seller_app.core.database.model.CategoryEntity
import com.example.seller_app.core.database.model.ColorEntity
import com.example.seller_app.core.database.model.SizeEntity
import com.example.seller_app.core.database.model.SubcategoryEntity
import com.example.seller_app.core.database.model.VariationEntity


@Database(
    [
        CategoryEntity::class,
        SubcategoryEntity::class,
        ColorEntity::class,
        SizeEntity::class,
    ],
    version = 2,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun subcategoryDao(): SubcategoryDao
    abstract fun colorDao(): ColorDao
    abstract fun sizeDao(): SizeDao

    companion object {

        @Volatile
        var INSTANCE: AppDatabase? = null

        fun getDatabase(ctx: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(ctx, AppDatabase::class.java, "app_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}