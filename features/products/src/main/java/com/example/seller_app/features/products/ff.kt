package com.example.seller_app.features.products

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.PositionalThreshold
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.pullToRefreshIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp


const val CROSSFADE_DURATION_MILLIS = 100
val SPINNER_SIZE = 16.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyCustomIndicator(
    state: PullToRefreshState,
    isRefreshing: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(Color.DarkGray)
            .pullToRefreshIndicator(
            state = state,
            isRefreshing = isRefreshing,
            containerColor = Color.Green,
            threshold = PositionalThreshold
        ),
        contentAlignment = Alignment.Center
    ) {
        Crossfade(
            targetState = isRefreshing,
            animationSpec = tween(durationMillis = CROSSFADE_DURATION_MILLIS),
            modifier = Modifier.align(Alignment.Center)
        ) { refreshing ->
            if (refreshing) {
                CircularProgressIndicator(Modifier.size(SPINNER_SIZE))
            } else {
                val distanceFraction = { state.distanceFraction.coerceIn(0f, 1f) }
                Icon(
                    imageVector = Icons.Filled.CloudDownload,
                    contentDescription = "Refresh",
                    tint = Color.Yellow,
                    modifier = Modifier
                        .size(18.dp)
                        .graphicsLayer {
                            val progress = distanceFraction()
                            this.alpha = progress
                            this.scaleX = progress
                            this.scaleY = progress
                        }
                )
            }
        }
    }
}
