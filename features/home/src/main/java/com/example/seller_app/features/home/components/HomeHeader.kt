package com.example.seller_app.features.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.seller_app.core.model.OrderStatus
import com.example.seller_app.core.ui.R

@Composable
internal fun HomeHeader(
    modifier: Modifier = Modifier,
    totalDeliveredOrders: Int,
    totalPendingOrders: Int,
    currentTab: OrderStatus,
    onSelectTab: (OrderStatus) -> Unit,
) {
    Surface(
        modifier = modifier,
        shadowElevation = 4.dp,
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                HeaderInfoCard(
                    modifier = Modifier.weight(1f),
                    description = "Encomendas Entregues",
                    value = "$totalDeliveredOrders",
                    icon = R.drawable.ic_basket,
                )
                HeaderInfoCard(
                    modifier = Modifier.weight(1f),
                    description = "Encomendas Pendentes",
                    value = "$totalPendingOrders",
                    valueColor = MaterialTheme.colorScheme.primary,
                    icon = R.drawable.ic_basket,
                )
            }
            Spacer(Modifier.height(40.dp))
            OrdersTab(
                modifier = Modifier.fillMaxWidth(),
                currentTab = currentTab,
                onSelectTab = { onSelectTab(it) },
            )
            Spacer(Modifier.height(14.dp))
        }
    }
}