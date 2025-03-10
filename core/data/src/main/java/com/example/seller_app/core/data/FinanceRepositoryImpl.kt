package com.example.seller_app.core.data

import com.example.seller_app.core.data.repositories.FinanceRepository
import com.example.seller_app.core.model.FinanceStatus
import com.example.seller_app.core.network.datasources.FinanceDataSource
import com.example.seller_app.core.network.model.response.asExternalModel

class FinanceRepositoryImpl(
    private val financeDataSource: FinanceDataSource
): FinanceRepository {

    override suspend fun getFinanceStatus(): Result<FinanceStatus> {
        return financeDataSource.getFinanceStatus().mapCatching { it.asExternalModel() }
    }

    override suspend fun requestWithdraw(): Result<Unit> {
        return financeDataSource.requestWithdraw()
    }
}