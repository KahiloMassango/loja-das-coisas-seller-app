package com.example.seller_app.core.data.workmanager

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.seller_app.core.data.repositories.PreferenceRepository
import com.example.seller_app.core.data.repositories.SizeRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class SizeSyncWorker @AssistedInject constructor (
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    @Assisted private val preferenceRepository: PreferenceRepository,
    @Assisted private val sizeRepository: SizeRepository,
): CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val firebaseTimestamp = 646546L
        val localTimestamp = preferenceRepository.getSizesLastUpdated()

        return /*if(localTimestamp == null || firebaseTimestamp > localTimestamp) {
            */try {
                sizeRepository.sync()
                //preferenceRepository.updateSizesLastUpdated(firebaseTimestamp)
                Result.success()
            } catch (e: Exception) {
                Result.retry()
            }
        /*} else {
            Result.success()
        }*/

    }
}