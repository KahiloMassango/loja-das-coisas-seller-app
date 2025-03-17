package com.example.seller_app.core.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.seller_app.core.database.dao.CategoryDao
import com.example.seller_app.core.database.dao.ColorDao
import com.example.seller_app.core.database.dao.GenderCategoryDao
import com.example.seller_app.core.database.dao.GenderDao
import com.example.seller_app.core.database.dao.SizeDao
import com.example.seller_app.core.database.model.CategoryEntity
import com.example.seller_app.core.database.model.ColorEntity
import com.example.seller_app.core.database.model.GenderCategory
import com.example.seller_app.core.database.model.GenderEntity
import com.example.seller_app.core.database.model.SizeEntity


@Database(
    [
        GenderCategory::class,
        GenderEntity::class,
        CategoryEntity::class,
        ColorEntity::class,
        SizeEntity::class,
    ],
    version = 4,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun genderDao(): GenderDao
    abstract fun categoryDao(): CategoryDao
    abstract fun colorDao(): ColorDao
    abstract fun sizeDao(): SizeDao
    abstract fun genderCategoryDao(): GenderCategoryDao


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