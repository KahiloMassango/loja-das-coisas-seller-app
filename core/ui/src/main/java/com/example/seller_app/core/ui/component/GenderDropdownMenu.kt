package com.example.seller_app.core.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.seller_app.core.model.Gender

@Composable
fun GenderDropdownMenu(
    modifier: Modifier = Modifier,
    selected: Gender?,
    genders: List<Gender>,
    enabled: Boolean = true,
    onSelect: (Gender) -> Unit,
) {
    Box(modifier = modifier) {
        var expanded by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = "Gênero",
                style = MaterialTheme.typography.bodySmall
            )
            OutlinedCard(
                modifier = Modifier,
                enabled = enabled,
                onClick = { expanded = true },
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(0.8f)),
                colors = CardDefaults.outlinedCardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = selected?.name ?: "Selecione uma opção",
                        style = MaterialTheme.typography.bodySmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            shadowElevation = 4.dp,
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ) {

            genders.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option.name,
                            style = MaterialTheme.typography.bodySmall
                        )
                    },
                    onClick = {
                        if (selected != option) {
                            onSelect(option)
                        }
                        expanded = false
                    }
                )
            }
        }
    }
}

