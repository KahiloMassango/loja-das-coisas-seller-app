package com.example.seller_app.features.product_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.example.seller_app.core.ui.component.LabeledTextField
import com.example.seller_app.core.ui.util.CurrencyVisualTransformation


@Composable
internal fun VariationPriceAndQuantity(
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
