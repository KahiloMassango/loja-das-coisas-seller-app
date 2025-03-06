package com.example.seller_app.features.variations.components

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.seller_app.core.model.product.Category
import com.example.seller_app.core.model.product.ProductItem
import com.example.seller_app.core.ui.component.CustomButton
import com.example.seller_app.core.ui.component.ImagePicker
import com.example.seller_app.core.ui.component.PriceAndQuantityContainer
import com.example.seller_app.core.ui.component.ProductVariationSelector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UpdateVariationModal(
    modifier: Modifier = Modifier,
    productItem: ProductItem,
    onDismissRequest: () -> Unit,
    category: Category,
    onUpdate: (ProductItem) -> Unit
) {
    var imageUrl by remember { mutableStateOf(productItem.imageUrl) }
    var price by remember { mutableStateOf(productItem.price.toString()) }
    var stockQuantity by remember { mutableStateOf(productItem.stockQuantity.toString()) }


    val context = LocalContext.current

    val isFormValid by remember {
        derivedStateOf {
            price.isNotBlank() && stockQuantity.isNotBlank()
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
                imageUri = imageUrl,
                onImageSelected = { imageUrl = it }
            )
            ProductVariationSelector(
                modifier = Modifier.fillMaxWidth(),
                category = category,
                enabled = false,
                selectedColor = productItem.color,
                selectedSize = productItem.size,
                onChangeColor = {},
                onChangeSize = {}
            )
            PriceAndQuantityContainer(
                price = price,
                stockQuantity = stockQuantity,
                onPriceChange = { price = it },
                onQuantityChange = { stockQuantity = it }
            )
            CustomButton(
                text = "Salvar",
                onClick = {
                    if (!isFormValid) {
                        Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        onUpdate(
                            productItem.copy(
                                imageUrl = imageUrl,
                                price = price.toInt(),
                                stockQuantity = stockQuantity.toInt()
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

