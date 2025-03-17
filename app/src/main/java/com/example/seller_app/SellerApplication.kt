package com.example.seller_app

import android.app.Application
import android.content.Context
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.seller_app.core.data.repositories.CategoryRepository
import com.example.seller_app.core.data.repositories.ColorRepository
import com.example.seller_app.core.data.repositories.GenderRepository
import com.example.seller_app.core.data.repositories.PreferenceRepository
import com.example.seller_app.core.data.repositories.SizeRepository
import com.example.seller_app.core.data.repositories.SyncRepository
import com.example.seller_app.core.data.workmanager.SyncWorker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class SellerApplication : Application(), Configuration.Provider {


    @Inject
    lateinit var syncWorkerFactory: SyncWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(syncWorkerFactory)
            .build()
}


class SyncWorkerFactory @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
    private val syncRepository: SyncRepository,
    private val categoryRepository: CategoryRepository,
    private val sizeRepository: SizeRepository,
    private val colorRepository: ColorRepository,
    private val genderRepository: GenderRepository

) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? = SyncWorker(
        appContext,
        workerParameters,
        preferenceRepository,
        syncRepository,
        categoryRepository,
        sizeRepository,
        colorRepository,
        genderRepository
    )
}
