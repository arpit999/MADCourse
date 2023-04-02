package com.example.madcourse.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.madcourse.R


@Composable
fun CircleImage(url: String?, size: Dp = 90.dp) {
    AsyncImage(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .border(border = BorderStroke(2.dp, Color.Black), CircleShape),
        model = ImageRequest.Builder(LocalContext.current).data(url).crossfade(true).build(),
        placeholder = painterResource(R.drawable.placeholder),
        error = painterResource(id = R.drawable.error),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )

}

@Composable
fun RectangleImage(
    modifier: Modifier = Modifier,
    height: Int = Int.MAX_VALUE,
    width: Int = Int.MAX_VALUE,
    url: String,
    onPostClick: () -> Unit
) {
    CompositionLocalProvider(LocalRippleTheme provides RippleCustomTheme) {
        ElevatedCard(
            modifier = modifier
                .size(height.dp, width.dp)
                .clickable { onPostClick() },
            elevation = CardDefaults.elevatedCardElevation(3.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(RectangleShape),
                model = ImageRequest.Builder(LocalContext.current).data(url).crossfade(true).build(),
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(id = R.drawable.error),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
    }
}

