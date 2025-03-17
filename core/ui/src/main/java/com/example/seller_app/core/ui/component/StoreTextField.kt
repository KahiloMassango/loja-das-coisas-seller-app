package com.example.seller_app.core.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.seller_app.core.ui.theme.SellerappTheme

@Composable
fun StoreTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    placeholder: String? = null,
    elevation: Dp = 2.dp,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    isError: Boolean = false,
    enabled: Boolean = true,
    supportingText: String? = null,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {

    Column {
        Text(
            modifier = Modifier.padding(bottom = 6.dp),
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        TextField(
            modifier = modifier
                .fillMaxWidth()
                .shadow(
                    elevation,
                    MaterialTheme.shapes.small
                ),
            value = value,
            textStyle = MaterialTheme.typography.bodySmall,
            isError = isError,
            leadingIcon = leadingIcon,
            singleLine = singleLine,
            maxLines = maxLines,
            enabled = enabled,
            onValueChange = { onValueChange(it) },
            trailingIcon = trailingIcon,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                disabledContainerColor = MaterialTheme.colorScheme.background,
                disabledTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                errorSupportingTextColor = MaterialTheme.colorScheme.error,
                errorIndicatorColor = Color.Unspecified,
                errorContainerColor = MaterialTheme.colorScheme.background
            ),
            placeholder = {
                if (placeholder != null) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
        )
        if (supportingText != null) {
            Text(
                modifier = Modifier.padding(start = 12.dp, top = 4.dp),
                text = supportingText,
                style = MaterialTheme.typography.bodySmall,
                color = if (isError) MaterialTheme.colorScheme.error
                else MaterialTheme.colorScheme.inverseOnSurface
            )
        }
    }
}

@Composable
fun LabeledTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    suffix: String,
    placeholder: String? = null,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        TextField(
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .border(
                    1.dp,
                    MaterialTheme.colorScheme.outlineVariant.copy(0.8f),
                    MaterialTheme.shapes.small
                ),
            value = value,
            singleLine = singleLine,
            maxLines = maxLines,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.bodySmall,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            placeholder = {
                if (placeholder != null) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
            suffix = {
                Text(
                    text = suffix,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            }
        )
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    SellerappTheme() {
        var value by remember {
            mutableStateOf("")
        }
        StoreTextField(
            label = "Password",
            value = value,
            onValueChange = { value = it },
            placeholder = "Password123",
            isError = false,
        )
    }
}