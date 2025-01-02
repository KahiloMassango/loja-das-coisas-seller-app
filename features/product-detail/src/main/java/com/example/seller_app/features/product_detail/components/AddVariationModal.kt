package com.example.seller_app.features.product_detail.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.union
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.seller_app.core.ui.component.CustomButton
import com.example.seller_app.core.ui.component.ImagePicker
import com.example.seller_app.core.ui.component.ProductVariationSelector
import com.example.seller_app.features.product_detail.model.VariationState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AddVariationModal(
    modifier: Modifier = Modifier,
    subCategory: String,
    colorOptions: List<String> = emptyList(),
    sizeOptions: List<String> = emptyList(),
    onAddVariation: (VariationState) -> Unit,
    onDismissRequest: () -> Unit,
) {
    val context = LocalContext.current
    var image: String? by rememberSaveable { mutableStateOf(null) }
    var color: String? by rememberSaveable { mutableStateOf(null) }
    var size: String? by rememberSaveable { mutableStateOf(null) }
    var price by rememberSaveable { mutableStateOf("1000") }
    var quantity by rememberSaveable { mutableStateOf("1") }

    val isFormValid by remember {
        derivedStateOf {
            (colorOptions.isEmpty() || color?.isNotEmpty() == true) && // Valid if no color options or a color is selected
            (sizeOptions.isEmpty() || size?.isNotEmpty() == true) &&  // Valid if no size options or a size is selected
            price.isNotBlank() &&                                    // Price must not be blank
            quantity.isNotBlank()
        }
    }

    ModalBottomSheet(
        sheetState = rememberModalBottomSheetState(true),
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface.copy(0.99f),
        scrimColor = Color(0xFF000000).copy(0.3f),
        contentColor = MaterialTheme.colorScheme.onSurface,
        onDismissRequest = onDismissRequest,
        contentWindowInsets = { WindowInsets.navigationBars.union(WindowInsets.ime) }
    ) {
        Column(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ImagePicker(
                imageUri = image,
                onImageSelected = { image = it }
            )
            ProductVariationSelector(
                modifier = Modifier.fillMaxWidth(),
                subCategory = subCategory,
                selectedColor = color,
                selectedSize = size,
                colorOptions = colorOptions,
                sizeOptions = sizeOptions,
                onColorSelected = { color = it },
                onSizeSelected = { size = it }
            )
            VariationPriceAndQuantity(
                price = price,
                quantity = quantity,
                onPriceChange = { price = it },
                onQuantityChange = { quantity = it }
            )
            CustomButton(
                text = "Adicionar",
                onClick = {
                    if (!isFormValid) {
                        Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        onAddVariation(
                            VariationState(
                                image = image,
                                color = color,
                                size = size,
                                price = price.toDouble(),
                                quantity = quantity.toInt()
                            )
                        )
                        Toast.makeText(context, "Adicionado com sucesso", Toast.LENGTH_SHORT)
                            .show()
                        onDismissRequest()
                    }
                }
            )
        }
    }
}

