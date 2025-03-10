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
import com.example.seller_app.core.data.workmanager.CategorySyncWorker
import com.example.seller_app.core.data.workmanager.ColorSyncWorker
import com.example.seller_app.core.data.workmanager.GenderSyncWorker
import com.example.seller_app.core.data.workmanager.SizeSyncWorker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class SellerApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var compositeWorkerFactory: CompositeWorkerFactory

    @Inject
    lateinit var categoryWorkerFactory: CategoryWorkerFactory

    @Inject
    lateinit var colorWorkerFactory: ColorsWorkerFactory

    @Inject
    lateinit var sizeWorkerFactory: SizesWorkerFactory

    @Inject
    lateinit var genderWorkerFactory: GenderWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(compositeWorkerFactory)
            .build()
}

class CompositeWorkerFactory @Inject constructor(
    private val categoryWorkerFactory: CategoryWorkerFactory,
    private val colorsWorkerFactory: ColorsWorkerFactory,
    private val sizesWorkerFactory: SizesWorkerFactory,
    private val genderWorkerFactory: GenderWorkerFactory
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when (workerClassName) {
            CategorySyncWorker::class.java.name ->
                categoryWorkerFactory.createWorker(appContext, workerClassName, workerParameters)

            ColorSyncWorker::class.java.name ->
                colorsWorkerFactory.createWorker(appContext, workerClassName, workerParameters)

            SizeSyncWorker::class.java.name ->
                sizesWorkerFactory.createWorker(appContext, workerClassName, workerParameters)

            GenderSyncWorker::class.java.name -> genderWorkerFactory.createWorker(
                appContext,
                workerClassName,
                workerParameters
            )

            else -> null
        }
    }
}

class CategoryWorkerFactory @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val preferenceRepository: PreferenceRepository

) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? = CategorySyncWorker(
        appContext,
        workerParameters,
        preferenceRepository,
        categoryRepository
    )
}

class ColorsWorkerFactory @Inject constructor(
    private val colorRepository: ColorRepository,
    private val preferenceRepository: PreferenceRepository

) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? = ColorSyncWorker(
        appContext,
        workerParameters,
        preferenceRepository,
        colorRepository
    )
}

class SizesWorkerFactory @Inject constructor(
    private val sizeRepository: SizeRepository,
    private val preferenceRepository: PreferenceRepository

) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? = SizeSyncWorker(
        appContext,
        workerParameters,
        preferenceRepository,
        sizeRepository
    )
}

class GenderWorkerFactory @Inject constructor(
    private val genderRepository: GenderRepository,
    private val preferenceRepository: PreferenceRepository

) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? = GenderSyncWorker(
        appContext,
        workerParameters,
        preferenceRepository,
        genderRepository
    )
}