package com.example.seller_app.features.home.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.seller_app.core.model.OrderStatus
import com.example.seller_app.core.ui.theme.SellerappTheme
import kotlin.collections.forEach


@Composable
internal fun OrdersTab(
    modifier: Modifier = Modifier,
    currentTab: OrderStatus,
    onSelectTab: (OrderStatus) -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        OrderStatus.entries.forEach { orderStatus ->
            OrderTabItem(
                modifier = Modifier.weight(1f),
                text = orderStatus.description,
                isSelected = currentTab == orderStatus,
                onClick = { onSelectTab(orderStatus) }
            )
        }
    }
}



@Composable
private fun OrderTabItem(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.secondary
        else Color.Companion.Transparent,
        label = ""
    )
    val textColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.onSecondary
        else MaterialTheme.colorScheme.onSurface,
        label = ""
    )

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(color = backgroundColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = textColor
        )
    }
}


@PreviewLightDark
@Composable
private fun Preview() {
    var currentTab by remember { mutableStateOf(OrderStatus.PENDING) }

    SellerappTheme() {
        OrdersTab(
            currentTab = currentTab,
            onSelectTab = { currentTab = it },
        )
    }
}

