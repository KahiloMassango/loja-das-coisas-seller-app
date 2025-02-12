package com.example.seller_app.features.products.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.seller_app.core.ui.component.AppDropdownMenu
import com.example.seller_app.core.ui.component.StoreSearchTextField

@Composable
internal fun ProductsHeader(
    modifier: Modifier = Modifier,
    selectedCategory: String,
    selectedSubCategory: String,
    categories: List<String>,
    subCategories: List<String>,
    onSelectCategory: (String) -> Unit,
    onSelectSubCategory: (String) -> Unit,
) {
    Surface(
        modifier = modifier,
        shadowElevation = 4.dp,
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Spacer(Modifier.height(8.dp))
            StoreSearchTextField(
                modifier = Modifier
                    .shadow(4.dp),
                query = "",
                placeholder = "Pesquisar",
                onQueryChange = {},
                onClearQuery = {},
                onSearch = {}
            )
            Spacer(Modifier.height(26.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                AppDropdownMenu(
                    modifier = Modifier.weight(1f),
                    label = "Gênero",
                    placeholder = "Selecione uma categoria",
                    selectedOption = selectedCategory,
                    options = categories,
                    onSelect = { onSelectCategory(it) }
                )
                AppDropdownMenu(
                    modifier = Modifier.weight(1f),
                    label = "Categoria",
                    placeholder = "Selecione uma categoria",
                    selectedOption = selectedSubCategory,
                    options = subCategories,
                    onSelect = { onSelectSubCategory(it) }
                )
            }
            Spacer(Modifier.height(20.dp))
        }
    }
}

