package com.example.seller_app.core.network.model.response

import com.example.seller_app.core.model.FinanceStatus
import com.example.seller_app.core.model.WithdrawRecord


data class FinanceStatusDtoRes(
    val balance: Int,
    val pending: List<StoreWithdrawDtoRes>,
    val completed: List<StoreWithdrawDtoRes>
)

fun FinanceStatusDtoRes.asExternalModel() =
    FinanceStatus(
        balance = balance,
        pending = pending.map { it.asExternalModel() },
        completed = completed.map { it.asExternalModel() }
    )

data class StoreWithdrawDtoRes(
    val amount: Int,
    val fee: Int,
    val feeAmount: Int,
    val total: Int,
    val requestDate: String
)

fun StoreWithdrawDtoRes.asExternalModel() =
    WithdrawRecord(
        amount = amount,
        fee = fee,
        feeAmount = feeAmount,
        total = total,
        requestDate = requestDate
    )
