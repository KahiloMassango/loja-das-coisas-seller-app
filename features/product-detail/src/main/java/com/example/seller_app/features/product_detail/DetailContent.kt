package com.example.seller_app.features.product_detail

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.seller_app.core.ui.component.AppDropdownMenu
import com.example.seller_app.core.ui.component.CenteredTopBar
import com.example.seller_app.core.ui.component.CustomButton
import com.example.seller_app.core.ui.component.ImagePicker
import com.example.seller_app.core.ui.component.StoreTextField


@Composable
internal fun DetailContent(
    modifier: Modifier = Modifier,
    viewModel: ProductDetailViewModel,
    onVariationsClick: () -> Unit,
    onNavigateUp: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var isEditing by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            CenteredTopBar(
                title = "Produtos",
                canNavigateBack = true,
                onNavigateUp = onNavigateUp,
                elevation = 4.dp,
            )
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures {
                        focusManager.clearFocus()
                    }
                }
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ImagePicker(
                imageUri = uiState.image,
                enabled = isEditing,
                onImageSelected = { viewModel.updateImage(it) }
            )
            Spacer(Modifier.height(30.dp))

            StoreTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.productName,
                onValueChange = { viewModel.updateProductName(it) },
                enabled = isEditing,
                label = "Nome do Produto",
                placeholder = "Ex.: T-Shirt Mangas Curtas",
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text,
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Next) }
                )
            )
            Spacer(Modifier.height(26.dp))
            StoreTextField(
                modifier = Modifier
                    .heightIn(110.dp),
                value = uiState.description,
                onValueChange = { viewModel.updateDescription(it) },
                enabled = isEditing,
                label = "Descrição",
                singleLine = false,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Ascii,
                )
            )
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AppDropdownMenu(
                    modifier = Modifier.weight(1f),
                    enabled = false,
                    label = "Categoria",
                    placeholder = "",
                    selectedOption = uiState.category,
                    options = emptyList(),
                    onSelect = { },
                )
                AppDropdownMenu(
                    modifier = Modifier.weight(1f),
                    enabled = false,
                    label = "Sub-Categoria",
                    placeholder = "",
                    selectedOption = uiState.subCategory,
                    options = emptyList(),
                    onSelect = { },
                )
            }
            Spacer(Modifier.height(20.dp))
            Row(
                // modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(30.dp),

                ) {
                if (!isEditing) {
                    CustomButton(
                        text = "Editar",
                        onClick = { isEditing = true }
                    )
                    CustomButton(
                        text = "Variações",
                        onClick = onVariationsClick
                    )
                } else {
                    CustomButton(
                        text = "Salvar",
                        onClick = {
                            isEditing = false
                        }
                    )
                }
            }
        }
    }
}
