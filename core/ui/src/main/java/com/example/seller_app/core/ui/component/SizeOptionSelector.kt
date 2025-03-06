package com.example.seller_app.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.seller_app.core.model.product.Size


@Composable
fun SizeOptionSelector(
    modifier: Modifier = Modifier,
    sizes: List<Size>,
    onSelect: (Size) -> Unit,
    onDismissRequest: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Box(
            modifier = modifier
                .clip(MaterialTheme.shapes.large)
                .background(
                    MaterialTheme.colorScheme.secondaryContainer,
                    MaterialTheme.shapes.large
                )
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxHeight(0.5f),
            ) {
                items(sizes) { option ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSelect(option)
                                onDismissRequest()
                            }
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(16.dp),
                            text = option.value,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}
