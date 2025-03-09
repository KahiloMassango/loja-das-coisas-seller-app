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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.seller_app.core.ui.component.AppCheckbox
import com.example.seller_app.core.ui.component.AppDropdownMenu
import com.example.seller_app.core.ui.component.CenteredTopBar
import com.example.seller_app.core.ui.component.CustomButton
import com.example.seller_app.core.ui.component.DeleteDialog
import com.example.seller_app.core.ui.component.ImagePicker
import com.example.seller_app.core.ui.component.StoreTextField
import com.example.seller_app.core.ui.toastMessage
import com.example.seller_app.features.product_detail.model.ProductUiState


@Composable
internal fun DetailContent(
    modifier: Modifier = Modifier,
    uiState: ProductUiState,
    updateImageUrl: (String) -> Unit,
    updateName: (String) -> Unit,
    onDelete: () -> Unit,
    updateDescription: (String) -> Unit,
    updateIsAvailable: (Boolean) -> Unit,
    onVariationClick: () -> Unit,
    onSaveUpdate: () -> Unit,
    onNavigateUp: () -> Unit
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    var isEditing by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }



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
                onImageSelected = { updateImageUrl(it) }
            )
            Spacer(Modifier.height(30.dp))

            StoreTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.productName,
                onValueChange = { updateName(it) },
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
                onValueChange = { updateDescription(it) },
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
                    label = "Gênero",
                    placeholder = "",
                    selectedOption = uiState.gender,
                    options = emptyList(),
                    onSelect = {},
                )
                AppDropdownMenu(
                    modifier = Modifier.weight(1f),
                    enabled = false,
                    label = "Categoria",
                    placeholder = "",
                    selectedOption = uiState.category.name,
                    options = emptyList(),
                    onSelect = {},
                )
            }
            Spacer(Modifier.height(20.dp))
            AppCheckbox(
                text = "Disponiblizar",
                checked = uiState.isAvailable,
                onCheckedChange = updateIsAvailable,
                enabled = isEditing
            )
            Spacer(Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(30.dp),

                ) {
                if (!isEditing) {
                    CustomButton(
                        text = "Editar",
                        onClick = { isEditing = true }
                    )
                    CustomButton(
                        text = "Variações",
                        onClick = onVariationClick
                    )
                } else {
                    CustomButton(
                        text = "Salvar",
                        onClick = {
                            if (uiState.productName.isNotBlank() && uiState.description.isNotBlank()) {
                                isEditing = false
                                onSaveUpdate()
                            } else {
                                context.toastMessage("Verifique campos vazios")
                            }
                        }
                    )
                    CustomButton(
                        text = "Eliminar",
                        onClick = { showDeleteDialog = true }
                    )
                }
            }
        }
        if (showDeleteDialog) {
            DeleteDialog(
                text = "Tem certeza que deseja excluir este producto?",
                onConfirm = {
                    onDelete()
                    showDeleteDialog = false
                },
                onDismissRequest = {
                    showDeleteDialog = false
                }
            )
        }
    }
}
