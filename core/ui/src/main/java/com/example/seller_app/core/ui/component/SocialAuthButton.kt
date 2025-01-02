package com.example.seller_app.core.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.seller_app.core.ui.R
import com.example.seller_app.core.ui.theme.SellerappTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SocialAuthButton(
    modifier: Modifier = Modifier,
    @DrawableRes
    iconRes: Int,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier,
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Image(
            modifier = Modifier
                .size(56.dp)
                .padding(vertical = 12.dp, horizontal = 18.dp),
            painter = painterResource(id = iconRes),
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun Preview() {
    SellerappTheme() {
        SocialAuthButton(
            iconRes = R.drawable.ic_launcher_foreground
        )
    }
}