package com.example.seller_app.features.add_product.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.seller_app.core.ui.component.AppDropdownMenu

@Composable
internal fun  ProductVariationSelector(
    modifier: Modifier = Modifier,
    subCategory: String,
    selectedColor: String?,
    selectedSize: String?,
    colorOptions: List<String>,
    sizeOptions: List<String>,
    onColorSelected: (String) -> Unit,
    onSizeSelected: (String) -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        when (subCategory) {
            "Calçados", "Roupas" -> {
                // Show both Color and Size dropdowns
                AppDropdownMenu(
                    modifier = Modifier.weight(0.5f),
                    label = "Cor",
                    placeholder = "Selecione uma cor",
                    selectedOption = selectedColor,
                    options = colorOptions,
                    onSelect = onColorSelected,
                )
                AppDropdownMenu(
                    modifier = Modifier.weight(0.5f),
                    label = "Tamanho",
                    placeholder = "Selecione um tamanho",
                    selectedOption = selectedSize,
                    options = sizeOptions,
                    onSelect = onSizeSelected,
                )
            }
            "Acessórios", "Maquiagem" -> {
                // Show only Color dropdown
                AppDropdownMenu(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    label = "Cor",
                    placeholder = "Selecione uma cor",
                    selectedOption = selectedColor,
                    options = colorOptions,
                    onSelect = onColorSelected,
                )
            }
            "Skincare", "Perfumes" -> {
                // Show only Size dropdown
                AppDropdownMenu(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    label = "Tamanho",
                    placeholder = "Selecione um tamanho",
                    selectedOption = selectedSize,
                    options = sizeOptions,
                    onSelect = onSizeSelected,
                )
            }
        }
    }
}
