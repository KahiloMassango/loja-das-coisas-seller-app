package com.example.seller_app.navigation

import androidx.navigation.NavController

internal fun NavController.navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
    navigate(topLevelDestination.route) {
        popUpTo(graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}