package com.example.seller_app.features.add_product.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.seller_app.core.model.product.ProductItemRequest
import com.example.seller_app.core.ui.R
import com.example.seller_app.core.ui.util.formatCurrency

@Composable
internal fun ProductItemRequestCard(
    modifier: Modifier = Modifier,
    productItemRequest: ProductItemRequest,
    onRemove: () -> Unit,
) {
    Card(
        modifier = modifier.height(104.dp),
        elevation = CardDefaults.elevatedCardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = productItemRequest.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(104.dp),
                error = painterResource(R.drawable.ic_launcher_background)
            )
            Row(
                modifier = Modifier
                    .weight(1f),
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        productItemRequest.color?.let {
                            AttributeDescription(
                                attribute = "Cor",
                                value = productItemRequest.color!!.name
                            )
                        }
                        productItemRequest.size?.let {
                            AttributeDescription(
                                attribute = "Tamanho",
                                value = productItemRequest.size!!.value
                            )
                        }
                    }
                    Text(
                        text = formatCurrency(productItemRequest.price),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                IconButton(
                    onClick = onRemove,
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
private fun AttributeDescription(
    modifier: Modifier = Modifier,
    attribute: String,
    value: String,
) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.inverseOnSurface,
                fontWeight = FontWeight.Light,
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )
        ) {
            append("$attribute: ")
        }
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Light,
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )
        ) {
            append(value)
        }
    }
    Text(modifier = modifier, text = annotatedString)
}
