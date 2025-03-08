package com.example.seller_app.core.data.workmanager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.seller_app.core.data.repositories.GenderRepository
import com.example.seller_app.core.data.repositories.PreferenceRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class GenderSyncWorker @AssistedInject constructor (
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    @Assisted private val preferenceRepository: PreferenceRepository,
    @Assisted private val genderRepository: GenderRepository,
): CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val remoteTimestamp = 646546L
        val localTimestamp = preferenceRepository.getSizesLastUpdated()

        return try {
            genderRepository.sync()
            preferenceRepository.updateSizesLastUpdated(remoteTimestamp)
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }

    }
}