package com.example.seller_app.features.order_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
internal fun OrderSummary(
    modifier: Modifier = Modifier,
    deliveryAddress: String,
    paymentType: String,
    deliveryMethod: String,
    total: String,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = "Informações do Pedido",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
        OrderSummaryDescription(title = "Endereço de Entrega", description = deliveryAddress)
        OrderSummaryDescription(title = "Método de pagamento", description = paymentType)
        OrderSummaryDescription(title = "Método de envio", description = deliveryMethod)
        OrderSummaryDescription(title = "Total", description = total, isTotal = true)
    }
}

@Composable
private fun OrderSummaryDescription(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    isTotal: Boolean = false
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(0.8f),
            text = "$title:",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.inverseOnSurface,
            fontWeight = FontWeight.Medium,
        )
        Text(
            modifier = Modifier.weight(1.5f),
            text = description,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Medium
            ),
            color = if (isTotal) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}
