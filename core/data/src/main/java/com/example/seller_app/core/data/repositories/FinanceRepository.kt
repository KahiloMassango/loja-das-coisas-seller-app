package com.example.seller_app.core.data.repositories

import com.example.seller_app.core.model.FinanceStatus

interface FinanceRepository {
    suspend fun getFinanceStatus(): Result<FinanceStatus>
    suspend fun requestWithdraw(): Result<Unit>
}