package com.example.seller_app.core.data.workmanager

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.seller_app.core.data.repositories.ColorRepository
import com.example.seller_app.core.data.repositories.PreferenceRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class ColorSyncWorker @AssistedInject constructor (
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    @Assisted private val preferenceRepository: PreferenceRepository,
    @Assisted private val colorRepository: ColorRepository,
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val firebaseTimestamp = 2427L
        val localTimestamp = preferenceRepository.getColorsLastUpdated()
        return try {
            colorRepository.sync()
            //preferenceRepository.updateColorsLastUpdated(firebaseTimestamp)
            Result.success()
        } catch (e: Exception) {
            Log.e("ColorSyncWorker", "doWork: ", e)
            Result.retry()
        }
    }
}