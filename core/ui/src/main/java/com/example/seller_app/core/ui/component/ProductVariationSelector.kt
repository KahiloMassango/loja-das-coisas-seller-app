package com.example.seller_app.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.seller_app.core.model.product.Category
import com.example.seller_app.core.model.product.Color
import com.example.seller_app.core.model.product.Size

@Composable
fun ProductVariationSelector(
    modifier: Modifier = Modifier,
    category: Category,
    selectedColor: Color?,
    selectedSize: Size?,
    enabled: Boolean = true,
    onChangeColor: () -> Unit,
    onChangeSize: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        when {
            category.hasSizeVariation && category.hasColorVariation -> {
                // Show both Color and Size dropdowns
                AppOptionSelector(
                    modifier = Modifier.weight(0.5f),
                    label = "Cor",
                    placeholder = "Selecione uma opção",
                    enabled = enabled,
                    selectedOption = selectedColor?.name ?: "",
                    onClick = onChangeColor,
                )
                AppOptionSelector(
                    modifier = Modifier.weight(0.5f),
                    label = "Tamanho",
                    placeholder = "Selecione uma opção",
                    enabled = enabled,
                    selectedOption = selectedSize?.value ?: "",
                    onClick = onChangeSize,
                )
            }

            category.hasColorVariation -> {
                // Show only Color dropdown
                AppOptionSelector(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    label = "Cor",
                    placeholder = "Selecione uma opção",
                    enabled = enabled,
                    selectedOption = selectedColor?.name ?: "",
                    onClick = onChangeColor,
                )
            }
            else -> {
                // Show only Size dropdown
                AppOptionSelector(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    label = "Tamanho",
                    placeholder = "Selecione uma opção",
                    enabled = enabled,
                    selectedOption = selectedSize?.value ?: "",
                    onClick = onChangeSize,
                )
            }
        }
    }
}
