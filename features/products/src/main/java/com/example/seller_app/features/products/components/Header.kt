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
internal fun Header(
    modifier: Modifier = Modifier,
    selectedCategory: String?,
    selectedGender: String?,
    genders: List<String>,
    categories: List<String>,
    onSelectCategory: (String?) -> Unit,
    onSelectGender: (String?) -> Unit,
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
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                AppDropdownMenu(
                    modifier = Modifier.weight(0.5f),
                    label = "Gênero",
                    placeholder = "Todos",
                    selectedOption = selectedGender,
                    options = listOf("Todos") + genders,
                    onSelect = {
                        if(it == "Todos") {
                            onSelectGender(null)
                        } else {
                            onSelectGender(it)
                        }
                    }
                )
                AppDropdownMenu(
                    modifier = Modifier.weight(0.5f),
                    label = "Categoria",
                    placeholder = "Todas",
                    selectedOption = selectedCategory,
                    options = listOf("Todas") + categories,
                    onSelect = {
                        if(it == "Todas") {
                            onSelectCategory(null)
                        } else {
                            onSelectCategory(it)
                        }
                    }
                )
            }
            Spacer(Modifier.height(20.dp))
        }
    }
}

