package com.example.madcourse.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.madcourse.domain.network.model.*
import com.example.madcourse.domain.network.utils.Constant
import com.example.madcourse.ui.components.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenA(profile: Profile?, users: LazyPagingItems<User>, onPostClick: (User) -> Unit) {

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
            items(items = users) { user ->
                if (user != null) {
                    RectangleImage(
                        url = user.profilePicture,
                        height = 90,
                        width = 90
                    ) {
                        onPostClick(user)
                    }
                }
            }

        }


        users.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    LoadingProgressBar(modifier = Modifier.fillMaxSize())
                }

                loadState.append is LoadState.Loading -> {
                    LoadingProgressBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }

                loadState.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && users.itemCount < 1 -> {
                    Text(text = "No more posts available")
                }

                loadState.refresh is LoadState.Error -> {}

                loadState.append is LoadState.Error -> {
                    RetryItem(
                        modifier = Modifier.fillMaxSize(),
                        onRetryClick = { retry() }
                    )
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

            CircleImage(url = profile?.picture)

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
fun LoadingProgressBar(
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    color: Color = MaterialTheme.colorScheme.primary,
    strokeWidth: Dp = 4.dp
) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(size)
                .align(Alignment.Center),
            color = color,
            strokeWidth = strokeWidth
        )
    }
}

inline fun <T : Any> LazyGridScope.items(
    items: LazyPagingItems<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline span: (LazyGridItemSpanScope.(item: T?) -> GridItemSpan)? = null,
    noinline contentType: (item: T?) -> Any? = { null },
    crossinline itemContent: @Composable LazyGridItemScope.(item: T?) -> Unit
) = items(
    count = items.itemCount,
    key = if (key != null) { index: Int -> items[index]?.let(key) ?: index } else null,
    span = if (span != null) {
        { span(items[it]) }
    } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    itemContent(items[it])
}

@Preview
@Composable
fun PreviewScreenA() {
//    ProfileDetails(profile = profile)
}