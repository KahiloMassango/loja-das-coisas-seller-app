package com.example.seller_app.core.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.seller_app.core.ui.R

@Composable
fun ProductRating(
    modifier: Modifier = Modifier,
    totalRatings: Int,
    rating: Int
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        for (starIndex in 1..5) {
            val isFilled = starIndex <= rating
            val starIcon = if (isFilled) Icons.Filled.Star else Icons.Outlined.Star
            val starColor =
                if (isFilled) Color(0xFFFFBA49) else MaterialTheme.colorScheme.onSurfaceVariant

            Icon(
                modifier = Modifier
                    .padding(end = 3.dp)
                    .size(12.dp),
                imageVector = starIcon,
                contentDescription = "Star $starIndex",
                tint = starColor
            )
        }
        Text(
            text = "($totalRatings)",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.inverseOnSurface,
        )
    }
}