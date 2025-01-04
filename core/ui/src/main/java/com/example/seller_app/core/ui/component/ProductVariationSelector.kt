package com.example.seller_app.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ProductVariationSelector(
    modifier: Modifier = Modifier,
    subCategory: String,
    selectedColor: String?,
    selectedSize: String?,
    enabled: Boolean = true,
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
                    enabled = enabled,
                    selectedOption = selectedColor,
                    onClick = onChangeColor,
                )
                AppOptionSelector(
                    modifier = Modifier.weight(0.5f),
                    label = "Tamanho",
                    placeholder = "Selecione um tamanho",
                    enabled = enabled,
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
                    enabled = enabled,
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
                    enabled = enabled,
                    selectedOption = selectedSize,
                    onClick  = onChangeSize,
                )
            }
        }
    }
}


@Preview
@Composable
fun VariationOptionSelector(
    modifier: Modifier = Modifier,
    options: List<String> = listOf("abc", "fggg", "fdgsdfg", "sfdgfsd", "sfdgsdfg", "gfs", "rtyr"),
    onSelect: (String) -> Unit = {},
    onDismissRequest: () -> Unit = {},
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
        ){
            LazyColumn(
                modifier = Modifier.fillMaxHeight(0.5f),
            ) {
                items(options) { option ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSelect(option)
                                onDismissRequest()
                            }
                    ){
                        Text(
                            modifier = Modifier
                                .padding(16.dp),
                            text = option,
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


