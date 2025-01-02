package com.example.seller_app.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.seller_app.core.ui.component.AppDropdownMenu

@Composable
fun ProductVariationSelector(
    modifier: Modifier = Modifier,
    subCategory: String,
    selectedColor: String?,
    selectedSize: String?,
    enabled: Boolean = true,
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
                    modifier = Modifier.weight(1f),
                    label = "Cor",
                    placeholder = "Selecione a cor",
                    selectedOption = selectedColor,
                    options = colorOptions,
                    onSelect = onColorSelected,
                    enabled = enabled
                )
                AppDropdownMenu(
                    modifier = Modifier.weight(1f),
                    label = "Tamanho",
                    placeholder = "Selecione o tamanho",
                    selectedOption = selectedSize,
                    options = sizeOptions,
                    onSelect = onSizeSelected,
                    enabled = enabled
                )
            }
            "Acessórios" -> {
                // Show only Color dropdown
                AppDropdownMenu(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    label = "Cor",
                    placeholder = "Selecione a cor",
                    selectedOption = selectedColor,
                    options = colorOptions,
                    onSelect = onColorSelected,
                    enabled = enabled
                )
            }
            "SkinCare", "Maquiagem", "Perfumes" -> {
                // Show only Size dropdown
                AppDropdownMenu(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    label = "Tamanho",
                    placeholder = "Selecione o tamanho",
                    selectedOption = selectedSize,
                    options = sizeOptions,
                    onSelect = onSizeSelected,
                    enabled = enabled
                )
            }
        }
    }
}
