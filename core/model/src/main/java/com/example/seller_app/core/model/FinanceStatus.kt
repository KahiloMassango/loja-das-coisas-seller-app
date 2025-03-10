package com.example.seller_app.core.model

data class FinanceStatus(
    val balance: Int,
    val pending: List<WithdrawRecord>,
    val completed: List<WithdrawRecord>
)


data class WithdrawRecord(
    val amount: Int,
    val fee: Int,
    val feeAmount: Int,
    val total: Int,
    val requestDate: String
)
