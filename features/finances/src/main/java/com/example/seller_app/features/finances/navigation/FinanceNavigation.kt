package com.example.seller_app.features.finances.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.seller_app.features.finances.FinancesScreen
import kotlinx.serialization.Serializable


@Serializable
data object FinanceRoute

fun NavController.navigateToFinances() = navigate(FinanceRoute)

fun NavGraphBuilder.financeScreen() {
    composable<FinanceRoute> {
        FinancesScreen()
    }

}