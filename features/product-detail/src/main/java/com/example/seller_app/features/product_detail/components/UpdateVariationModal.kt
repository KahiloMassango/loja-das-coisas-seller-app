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
import com.example.seller_app.core.model.product.VariationItem
import com.example.seller_app.core.ui.component.CustomButton
import com.example.seller_app.core.ui.component.ImagePicker
import com.example.seller_app.core.ui.component.ProductVariationSelector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UpdateVariationModal(
    modifier: Modifier = Modifier,
    variationItem: VariationItem,
    onDismissRequest: () -> Unit,
    subCategory: String,
    onUpdateVariation: (VariationItem) -> Unit
) {
    var image by rememberSaveable { mutableStateOf(variationItem.image) }
    var price by rememberSaveable { mutableStateOf(variationItem.price.toString()) }
    var quantity by rememberSaveable { mutableStateOf(variationItem.stockQuantity.toString()) }

    val context = LocalContext.current

    val isFormValid by remember {
        derivedStateOf {
            image != null &&         // Image must not be blank
            price.isNotBlank() &&   // Price must not be blank
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
                enabled = false,
                selectedColor = variationItem.color,
                selectedSize = variationItem.size,
                onChangeColor = {},
                onChangeSize = {}
            )
            VariationPriceAndQuantity(
                price = price,
                quantity = quantity,
                onPriceChange = { price = it },
                onQuantityChange = { quantity = it }
            )
            CustomButton(
                text = "Salvar",
                onClick = {
                    if (!isFormValid) {
                        Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        onUpdateVariation(
                            variationItem.copy(
                                image = image,
                                price = price.toDouble(),
                                stockQuantity = quantity.toInt()
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

