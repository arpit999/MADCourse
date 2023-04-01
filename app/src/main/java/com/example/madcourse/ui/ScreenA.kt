package com.example.madcourse.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.madcourse.R
import com.example.madcourse.domain.network.model.*
import com.example.madcourse.ui.components.HorizontalSpacer
import com.example.madcourse.ui.components.RippleCustomTheme
import com.example.madcourse.ui.components.VerticalSpacer


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenA(profile: Profile?, posts: SnapshotStateList<Post>, onPostClick: (Post) -> Unit) {

    Scaffold(topBar = {
        Surface(shadowElevation = 3.dp) {
            TopAppBar(title = { Text(text = "User Profile") })
        }
    }) { paddingValues ->

        LazyVerticalGrid(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 12.dp),
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Profile Description
            item(span = { GridItemSpan(3) }) { ProfileDetails(profile = profile) }

            // Posts
            items(posts) { post ->
                RectangleImage(url = post.downloadUrl) {
                    onPostClick(post)
                }
            }

        }

    }
}


@Composable
fun ProfileDetails(modifier: Modifier = Modifier, profile: Profile?) {

    Column(
        modifier = modifier.padding(vertical = 12.dp)
    ) {
        Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .border(border = BorderStroke(2.dp, Color.Black), CircleShape),
                model = profile?.picture,
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null
            )

            HorizontalSpacer(size = 16)

            Column {
                profile?.let { profile ->
                    Text(text = profile.getFullName(), style = MaterialTheme.typography.titleLarge)
                    Text(
                        text = profile.employment.title,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray
                    )
                    Text(text = profile.email, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                }
            }
        }


        Column(Modifier.padding(vertical = 12.dp, horizontal = 8.dp)) {
            Text(text = "Address", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
            Divider()
            profile?.address?.let { address ->
                VerticalSpacer(size = 4)
                Text(
                    text = address.streetName + " " + address.streetAddress,
                    style = MaterialTheme.typography.bodyLarge, color = Color.Gray
                )
                Text(
                    text = address.zipCode + ", " + address.city + " " + address.state,
                    style = MaterialTheme.typography.bodyLarge, color = Color.Gray
                )
                Text(text = address.country, style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
            }
        }

        //SUBSCRIPTION
        Column(Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
            Text(text = "Subscription", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
            Divider()
            profile?.subscription?.let { subscription ->
                VerticalSpacer(size = 4)
                Text(
                    text = subscription.plan + " (" + subscription.term + ")",
                    style = MaterialTheme.typography.bodyLarge, color = Color.Gray
                )
                Text(
                    text = "Payment Method " + subscription.paymentMethod,
                    style = MaterialTheme.typography.bodyLarge, color = Color.Gray
                )
            }
        }


    }
}

@Composable
fun RectangleImage(url: String, onPostClick: () -> Unit) {

    CompositionLocalProvider(LocalRippleTheme provides RippleCustomTheme) {
        ElevatedCard(
            modifier = Modifier
                .size(90.dp)
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


@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(42.dp)
                .height(42.dp)
                .padding(8.dp),
            strokeWidth = 5.dp
        )

    }
}


@Preview
@Composable
fun PreviewScreenA() {
//    ProfileDetails(profile = profile)
}