package com.example.seller_app.core.ui.component

import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddVariationFAB(onClick: () -> Unit) {
    FloatingActionButton(
        modifier = Modifier.widthIn(max = 140.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = onClick
    ) {
        /* Row(
             modifier = Modifier.padding(horizontal = 10.dp),
             horizontalArrangement = Arrangement.spacedBy(8.dp),
             verticalAlignment = Alignment.CenterVertically
         ) {*/
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary
        )
        /*Text(
            text = "Adicionar Variação",
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )*/
        //}
    }
}
