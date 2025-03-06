package com.example.seller_app.core.data.workmanager

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.seller_app.core.data.repositories.CategoryRepository
import com.example.seller_app.core.data.repositories.PreferenceRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


@HiltWorker
class CategorySyncWorker @AssistedInject constructor (
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    @Assisted private val preferenceRepository: PreferenceRepository,
    @Assisted private val categoryRepository: CategoryRepository,
): CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val firebaseTimestamp = 645364L
        val localTimestamp = preferenceRepository.getCategoriesLastUpdated()

       return if(localTimestamp == null || firebaseTimestamp > localTimestamp) {
           try {
               categoryRepository.sync()
               preferenceRepository.updateCategoriesLastUpdated(firebaseTimestamp)
               Result.success()
           } catch (e: Exception) {
               Result.retry()
           }
        } else {
            Result.success()
       }

    }
}