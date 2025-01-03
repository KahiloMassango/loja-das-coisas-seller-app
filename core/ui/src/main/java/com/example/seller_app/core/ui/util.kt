package com.example.seller_app.core.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput

fun Modifier.clearFocusOnTap(focusManager: FocusManager) = this.pointerInput(Unit) {
    detectTapGestures {
        focusManager.clearFocus()
    }
}

fun Context.toastMessage(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()