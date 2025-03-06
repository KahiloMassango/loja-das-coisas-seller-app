package com.example.seller_app.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.seller_app.core.model.OrderStatus
import com.example.seller_app.core.ui.component.CenteredTopBar
import com.example.seller_app.core.ui.theme.SellerappTheme
import com.example.seller_app.features.home.components.HomeHeader
import com.example.seller_app.features.home.components.OrderCard

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeScreenContent(
        modifier = modifier
    )

}


@Composable
private fun HomeScreenContent(modifier: Modifier) {
    var currentTab by remember { mutableStateOf(OrderStatus.PENDING) }
    Scaffold(
        modifier = modifier,
        topBar = {
            CenteredTopBar(
                title = "Store Name",
                canNavigateBack = false,
                action = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        contentWindowInsets = WindowInsets.systemBars.exclude(BottomAppBarDefaults.windowInsets),
        containerColor = MaterialTheme.colorScheme.surface
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            HomeHeader(
                totalDeliveredOrders = 10,
                totalPendingOrders = 5,
                currentTab = currentTab,
                onSelectTab = { currentTab = it }
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(5) {
                    OrderCard()
                }
            }
        }
    }
}



@Preview
@Composable
private fun Preview() {
    SellerappTheme {
        HomeScreen()
    }
}
