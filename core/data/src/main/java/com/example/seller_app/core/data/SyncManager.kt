package com.example.seller_app.core.data

import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.seller_app.core.data.workmanager.CategorySyncWorker
import com.example.seller_app.core.data.workmanager.ColorSyncWorker
import com.example.seller_app.core.data.workmanager.GenderSyncWorker
import com.example.seller_app.core.data.workmanager.SizeSyncWorker

class SyncManager(
    private val workManager: WorkManager,
) {

    fun sync() {
        workManager.beginUniqueWork(
            "sync",
            ExistingWorkPolicy.REPLACE,
            listOf(
                OneTimeWorkRequest.from(CategorySyncWorker::class.java),
                OneTimeWorkRequest.from(SizeSyncWorker::class.java),
                OneTimeWorkRequest.from(ColorSyncWorker::class.java),
                OneTimeWorkRequest.from(GenderSyncWorker::class.java)
            )
        ).enqueue()
    }

    fun syncCategories() {
        val workRequest = OneTimeWorkRequest.from(CategorySyncWorker::class.java)

        workManager.enqueueUniqueWork(
            "syncCategories",
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
    }

    fun syncSizes() {
        val workRequest = OneTimeWorkRequest.from(SizeSyncWorker::class.java)

        workManager.enqueueUniqueWork(
            "syncSizes",
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
    }

    fun syncColors() {
        val workRequest = OneTimeWorkRequest.from(ColorSyncWorker::class.java)

        workManager.enqueueUniqueWork(
            "syncColors",
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
    }

    fun syncGenders() {
        val workRequest = OneTimeWorkRequest.from(GenderSyncWorker::class.java)

        workManager.enqueueUniqueWork(
            "syncGenders",
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
    }

}