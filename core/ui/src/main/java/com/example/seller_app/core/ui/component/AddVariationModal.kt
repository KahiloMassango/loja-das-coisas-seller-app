package com.example.seller_app.core.ui.component

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.seller_app.core.model.product.Category
import com.example.seller_app.core.model.product.Color
import com.example.seller_app.core.model.product.ProductItemRequest
import com.example.seller_app.core.model.product.Size

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddVariationModal(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    category: Category,
    colorOptions: List<Color>,
    sizeOptions: List<Size>,
    onAddVariation: (ProductItemRequest) -> Unit
) {


    var imageUrl by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var stockQuantity by remember { mutableStateOf("") }
    var size by remember { mutableStateOf<Size?>(null) }
    var color by remember { mutableStateOf<Color?>(null) }

    var showColorSelector by remember { mutableStateOf(false) }
    var showSizeSelector by remember { mutableStateOf(false) }
    val context = LocalContext.current

    ModalBottomSheet(
        sheetState = rememberModalBottomSheetState(true),
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface.copy(0.99f),
        scrimColor = androidx.compose.ui.graphics.Color(0xFF000000).copy(0.3f),
        contentColor = MaterialTheme.colorScheme.onSurface,
        onDismissRequest = onDismissRequest,
        contentWindowInsets = { WindowInsets.navigationBars.union(WindowInsets.ime) }
    ) {
        val focusManager = LocalFocusManager.current
        Column(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ImagePicker(
                imageUri = imageUrl,
                onImageSelected = { imageUrl = it }
            )
            ProductVariationSelector(
                modifier = Modifier.fillMaxWidth(),
                category = category,
                selectedColor = color,
                selectedSize = size,
                onChangeColor = { showColorSelector = true },
                onChangeSize = { showSizeSelector = true }
            )

            PriceAndQuantityContainer(
                price = price,
                stockQuantity = stockQuantity,
                onPriceChange = { price = it },
                onQuantityChange = { stockQuantity = it }
            )
            CustomButton(
                text = "Adicionar",
                onClick = {
                    if (!isValidProductItem(category, color, size, stockQuantity, price) || imageUrl.isBlank()) {
                        Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        onAddVariation(
                            ProductItemRequest(
                                stockQuantity = stockQuantity.toInt(),
                                price = price.toInt(),
                                imageUrl = imageUrl,
                                size = size,
                                color = color
                            )
                        )
                        onDismissRequest()
                        focusManager.clearFocus()
                    }
                }
            )
        }
    }
    if (showColorSelector) {
        ColorOptionSelector(
            colors = colorOptions,
            onSelect = { color = it },
            onDismissRequest = { showColorSelector = false }
        )
    }
    if (showSizeSelector) {
        SizeOptionSelector(
            sizes = sizeOptions,
            onSelect = { size = it },
            onDismissRequest = { showSizeSelector = false }
        )
    }

}

private fun isValidProductItem(
    category: Category,
    color: Color?,
    size: Size?,
    stockQuantity: String,
    price: String
): Boolean {
    return if (category.hasSizeVariation && category.hasColorVariation) {
        (((color != null && size != null
                && (stockQuantity.toIntOrNull() ?: 0) > 0 && (price.toDoubleOrNull()
            ?: 0.0) > 0.1)))
    } else if (category.hasSizeVariation) {
        (size != null && (stockQuantity.toIntOrNull() ?: 0) > 0
                && (price.toDoubleOrNull() ?: 0.0) > 0.1)
    } else {
        (color != null && (stockQuantity.toIntOrNull() ?: 0) > 0
                && (price.toDoubleOrNull() ?: 0.0) > 0.1)
    }
}






