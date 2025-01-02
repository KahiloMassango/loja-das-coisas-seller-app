package com.example.seller_app.features.add_product.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.example.seller_app.core.ui.component.CustomButton
import com.example.seller_app.core.ui.component.ImagePicker
import com.example.seller_app.core.ui.component.LabeledTextField
import com.example.seller_app.features.add_product.VariationItem
import com.example.seller_app.core.ui.util.CurrencyVisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AddVariationModal(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    subCategory: String,
    colorOptions: List<String> = emptyList(),
    sizeOptions: List<String> = emptyList(),
    onAddVariation: (VariationItem) -> Unit
) {
    var variation by remember { mutableStateOf(VariationItem()) }
    val context = LocalContext.current

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
                imageUri = variation.image,
                onImageSelected = { variation = variation.copy(image = it) }
            )
            ProductVariationSelector(
                modifier = Modifier.fillMaxWidth(),
                subCategory = subCategory,
                selectedColor = variation.color,
                selectedSize = variation.size,
                colorOptions = colorOptions,
                sizeOptions = sizeOptions,
                onColorSelected = { variation = variation.copy(color = it) },
                onSizeSelected = { variation = variation.copy(size = it) }
            )
            VariationPriceAndQuantity(
                price = variation.price,
                quantity = variation.quantity,
                onPriceChange = { variation = variation.copy(price = it) },
                onQuantityChange = { variation = variation.copy(quantity = it) }
            )
            CustomButton(
                text = "Adicionar",
                onClick = {
                    if (
                        (colorOptions.isNotEmpty() && variation.color.isNullOrEmpty()) ||
                        (sizeOptions.isNotEmpty() && variation.size.isNullOrEmpty()) ||
                        variation.price.isBlank() || variation.quantity.isBlank() ||
                        variation.image == null
                    ) {
                        Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        onAddVariation(variation)
                        variation = VariationItem()
                    }
                }
            )
        }
    }
}


@Composable
private fun VariationPriceAndQuantity(
    modifier: Modifier = Modifier,
    price: String,
    quantity: String,
    onPriceChange: (String) -> Unit,
    onQuantityChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        LabeledTextField(
            modifier = Modifier.width(126.dp),
            label = "Preço",
            suffix = "Kz",
            value = price,
            onValueChange = { newValue ->
                val digitsOnly = newValue.replace(Regex("\\D"), "")
                if ((digitsOnly.toLongOrNull() ?: 0L) < 500000L) {
                    onPriceChange(digitsOnly)
                }
            },
            visualTransformation = CurrencyVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Next) }
            )

        )
        LabeledTextField(
            modifier = Modifier.width(110.dp),
            label = "Quantidade",
            suffix = "unid.",
            value = quantity,
            onValueChange = {
                if (it.isDigitsOnly() && it.length <= 3) {
                    onQuantityChange(it)
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus(true) }
            )

        )
    }
}
