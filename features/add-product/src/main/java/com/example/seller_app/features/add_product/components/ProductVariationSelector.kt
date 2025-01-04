package com.example.seller_app.features.add_product.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.seller_app.core.ui.component.AppOptionSelector

@Composable
internal fun  ProductVariationSelector(
    modifier: Modifier = Modifier,
    subCategory: String,
    selectedColor: String?,
    selectedSize: String?,
    onChangeColor: () -> Unit,
    onChangeSize: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        when (subCategory) {
            "Calçados", "Roupas" -> {
                // Show both Color and Size dropdowns
                AppOptionSelector(
                    modifier = Modifier.weight(0.5f),
                    label = "Cor",
                    placeholder = "Selecione uma cor",
                    selectedOption = selectedColor,
                    onClick = onChangeColor,
                )
                AppOptionSelector(
                    modifier = Modifier.weight(0.5f),
                    label = "Tamanho",
                    placeholder = "Selecione um tamanho",
                    selectedOption = selectedSize,
                    onClick  = onChangeSize,
                )
            }
            "Acessórios", "Maquiagem" -> {
                // Show only Color dropdown
                AppOptionSelector(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    label = "Cor",
                    placeholder = "Selecione uma cor",
                    selectedOption = selectedColor,
                    onClick = onChangeColor,
                )
            }
            "Skincare", "Perfumes" -> {
                // Show only Size dropdown
                AppOptionSelector(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    label = "Tamanho",
                    placeholder = "Selecione um tamanho",
                    selectedOption = selectedSize,
                    onClick  = onChangeSize,
                )
            }
        }
    }
}
