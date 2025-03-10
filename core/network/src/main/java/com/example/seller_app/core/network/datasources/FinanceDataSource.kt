package com.example.seller_app.core.network.datasources

import com.example.seller_app.core.network.model.response.FinanceStatusDtoRes

interface FinanceDataSource {
    suspend fun getFinanceStatus(): Result<FinanceStatusDtoRes>
    suspend fun requestWithdraw(): Result<Unit>
}