package com.example.seller_app.features.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.seller_app.core.ui.PhonePreviews
import com.example.seller_app.core.ui.theme.SellerappTheme


@Composable
internal fun HeaderInfoCard(
    modifier: Modifier = Modifier,
    description: String,
    value: String,
    valueColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    @DrawableRes icon: Int,
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(10),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconContainer(icon)
            Column {
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodySmall,
                    color = valueColor,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun IconContainer(@DrawableRes icon: Int) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(RoundedCornerShape(12))
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = Color(0xFFF9F9F9)
        )
    }
}

@PhonePreviews
@Composable
private fun Preview() {
    SellerappTheme {
        HeaderInfoCard(
            description = "Pendentes",
            value = "10",
            icon = com.example.seller_app.core.ui.R.drawable.ic_basket,
        )
    }
}