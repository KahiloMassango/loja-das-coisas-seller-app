package com.example.seller_app.core.data

import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.seller_app.core.data.workmanager.SyncWorker
import com.example.seller_app.core.network.datasources.SyncNetworkDatasource

class SyncManager(
    private val workManager: WorkManager,
    private val syncNetworkDatasource: SyncNetworkDatasource,
) {

    fun sync() {
        val workRequest = OneTimeWorkRequest.Builder(SyncWorker::class.java)
            .build()

        workManager.enqueue(workRequest)
    }

}