package com.example.seller_app.core.ui.component


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun DeleteConfirmationDialog(
    modifier: Modifier = Modifier,
    text: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Box(
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colorScheme.secondaryContainer, MaterialTheme.shapes.large)
        ){
            Column(
                modifier = modifier
                    .padding(20.dp)
                ,//.fillMaxWidth(0.9f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Confirmar Exclusão",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomOutlinedButton(
                        text = "Cancelar",
                        onClick = onDismiss
                    )
                    Spacer(Modifier.width(14.dp))
                    CustomButton(
                        text = "Excluir",
                        onClick = {
                            onConfirm()
                            onDismiss()
                        }
                    )
                }
            }
        }
    }
}
