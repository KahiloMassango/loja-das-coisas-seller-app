package com.example.seller_app.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.seller_app.core.ui.theme.SellerappTheme


@Composable
fun AppNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val showBottomBar = currentDestination?.hierarchy?.any { destination ->
        topLevelRoutes.any { destination.hasRoute(it) }
    } == true

    AnimatedVisibility(
        modifier = modifier
            .shadow(14.dp, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .zIndex(1f),
        visible = showBottomBar,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
    ) {
        NavigationBar(
            modifier = Modifier,
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ) {
            TopLevelDestination.entries.forEach { destination ->
                val selected = currentDestination?.hasRoute(destination.route::class) ?: false
                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        navController.navigateToTopLevelDestination(destination)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(destination.icon),
                            contentDescription = null,
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = MaterialTheme.colorScheme.secondaryContainer,
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.inverseOnSurface,
                    )
                )
            }
        }

    }
}


@Preview
@Composable
private fun Preview() {
    SellerappTheme {
        AppNavigationBar()
    }
}