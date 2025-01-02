package com.example.seller_app.core.ui.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.NumberFormat
import java.util.Locale


fun CurrencyVisualTransformation(): VisualTransformation {
    return VisualTransformation { text ->
        // Clean the input to contain only digits
        val cleanedText = text.text.replace(Regex("[^\\d]"), "")
        if(cleanedText.isBlank()) {
            return@VisualTransformation TransformedText(
                text = AnnotatedString(""), // Show nothing for empty input
                offsetMapping = OffsetMapping.Identity
            )
        }
        val formattedText = if (cleanedText.isNotEmpty()) {
            val number = cleanedText.toLongOrNull() ?: 0L
            NumberFormat.getNumberInstance(Locale("pt", "AO")).format(number)
        } else {
            ""
        }

        // Build a mapping for cursor offsets
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                var digitCount = 0
                for (i in formattedText.indices) {
                    if (formattedText[i].isDigit()) {
                        digitCount++
                        if (digitCount == offset + 1) {
                            return i + 1
                        }
                    }
                }
                return formattedText.length
            }

            override fun transformedToOriginal(offset: Int): Int {
                var digitCount = 0
                for (i in 0 until offset.coerceAtMost(formattedText.length)) {
                    if (formattedText[i].isDigit()) {
                        digitCount++
                    }
                }
                return digitCount
            }
        }

        TransformedText(AnnotatedString(formattedText), offsetMapping)
    }
}

