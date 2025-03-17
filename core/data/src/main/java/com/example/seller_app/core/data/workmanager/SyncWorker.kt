package com.example.seller_app.core.data.workmanager

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.seller_app.core.data.repositories.CategoryRepository
import com.example.seller_app.core.data.repositories.ColorRepository
import com.example.seller_app.core.data.repositories.GenderRepository
import com.example.seller_app.core.data.repositories.PreferenceRepository
import com.example.seller_app.core.data.repositories.SizeRepository
import com.example.seller_app.core.data.repositories.SyncRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    @Assisted private val preferenceRepository: PreferenceRepository,
    @Assisted private val syncRepository: SyncRepository,
    @Assisted private val categoryRepository: CategoryRepository,
    @Assisted private val sizeRepository: SizeRepository,
    @Assisted private val colorRepository: ColorRepository,
    @Assisted private val genderRepository: GenderRepository
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val remoteTimestamp = syncRepository.getLastUpdated().getOrNull()
            ?: return Result.retry()
        val localTimestamp = preferenceRepository.getLastUpdated()
        Log.d("SyncWorker", "doWork: loca: $localTimestamp remote: $remoteTimestamp")
        return if (localTimestamp == null || remoteTimestamp > localTimestamp ){
            Log.d("SyncWorker", "doWork: Need update")
           try {
               val syncData = syncRepository.getSyncData().getOrThrow()
               categoryRepository.sync(syncData.categories)
               sizeRepository.sync(syncData.sizes)
               colorRepository.sync(syncData.colors)
               genderRepository.sync(syncData.genders)
               genderRepository.syncGenderCategories(syncData.genderCategoryRelations)
               preferenceRepository.updateLastUpdated(remoteTimestamp)

               return Result.success()
           } catch (e: Exception) {
               Log.d("SyncWorker", "doWork: exception $e")
               e.printStackTrace()
               Result.retry()
           }
        } else {
            Log.d("SyncWorker", "doWor: no Need update")
            Result.success()
        }
    }
}