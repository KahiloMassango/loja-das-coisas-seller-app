package com.example.seller_app.features.add_product

import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.seller_app.core.ui.clearFocusOnTap
import com.example.seller_app.core.ui.component.AppDropdownMenu
import com.example.seller_app.core.ui.component.CenteredTopBar
import com.example.seller_app.core.ui.component.CustomButton
import com.example.seller_app.core.ui.component.ImagePicker
import com.example.seller_app.core.ui.component.StoreTextField

@Composable
internal fun ProductDetailContent(
    modifier: Modifier = Modifier,
    uiState: ProductUiState,
    onProductNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onImageChange: (String) -> Unit,
    categories: List<String>,
    subCategories: List<String>,
    onCategoryChange: (String) -> Unit,
    onSubCategoryChange: (String) -> Unit,
    onVariationsClick: () -> Unit,
    onSaveProduct: () -> Unit,
    onNavigateUp: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    Scaffold(
        modifier = modifier,
        topBar = {
            CenteredTopBar(
                title = "Produto",
                canNavigateBack = true,
                onNavigateUp = onNavigateUp,
                elevation = 4.dp
            )
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .clearFocusOnTap(focusManager)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ImagePicker(
                imageUri = uiState.image,
                onImageSelected = { onImageChange(it) }
            )
            Spacer(Modifier.height(30.dp))
            StoreTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.productName,
                onValueChange = { onProductNameChange(it) },
                label = "Nome do Produto",
                placeholder = "Ex.: T-Shirt Mangas Curtas",
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Next) }
                )
            )
            Spacer(Modifier.height(26.dp))
            StoreTextField(
                modifier = Modifier
                    .height(115.dp),
                value = uiState.description,
                onValueChange = { onDescriptionChange(it) },
                label = "Descrição",
                singleLine = false,
            )
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AppDropdownMenu(
                    modifier = Modifier.weight(1f),
                    label = "Categoria",
                    placeholder = "Selecione uma categoria",
                    selectedOption = uiState.category,
                    options = categories,
                    onSelect = { onCategoryChange(it) },
                )
                AppDropdownMenu(
                    modifier = Modifier.weight(1f),
                    label = "Sub-Categoria",
                    placeholder = "Selecione uma categoria",
                    selectedOption = uiState.subCategory,
                    options = subCategories,
                    onSelect = { onSubCategoryChange(it) },
                )
            }
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomButton(
                    text = "Salvar Produto",
                    onClick = onSaveProduct,
                    enabled = uiState.variations.isNotEmpty() &&
                            uiState.category != null &&
                            uiState.subCategory != null &&
                    uiState.productName.isNotBlank() &&
                    uiState.image.isNotBlank()
                )
                CustomButton(
                    text = "Variações",
                    onClick = {
                        if(uiState.productName.isBlank() && uiState.category == null ){
                            Toast.makeText(
                                context,
                                "Por favor, preencha todos os campos",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            onVariationsClick()
                        }
                    }
                )
            }
        }
    }
}


