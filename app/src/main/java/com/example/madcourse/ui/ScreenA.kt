package com.example.madcourse.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.example.madcourse.domain.UserViewModel
import com.example.madcourse.domain.network.model.User
import com.example.madcourse.ui.components.ClearIcon
import com.example.madcourse.ui.components.SearchIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenA(viewModel: UserViewModel, navController: NavHostController) {

//    val userList = viewModel.userList.collectAsStateWithLifecycle()

    val users = remember { viewModel.getGitHubUsers() }.collectAsLazyPagingItems()

    Scaffold(topBar = {
        Surface(shadowElevation = 3.dp) {
            TopAppBar(title = { Text(text = "User Directory") })
        }
    }) { paddingValues ->

        ScreenAContent(Modifier.padding(paddingValues),
//            userList = userList,
            viewModel = viewModel,
            users = users,
            onSearchClick = {
//                viewModel.getUsers(viewModel.getPageNumber())
                viewModel.getGitHubUsers()
            },
            onTextChanged = {
                viewModel.onSearchTextChanged(it)
            },
            onUserClick = {
                viewModel.getUserDetails(it.username)
                navController.navigate("${AppNavigation.SCREEN_B.name}/${it.username}")
            }
        )
    }
}

@Composable
fun ScreenAContent(
    modifier: Modifier = Modifier,
//    userList: State<List<User>>,
    onSearchClick: () -> Unit,
    onTextChanged: (String) -> Unit,
    onUserClick: (User) -> Unit,
    users: LazyPagingItems<User>,
    viewModel: UserViewModel
) {
    val searchText by viewModel.searchText.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp, horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = searchText,
            onValueChange = viewModel::onSearchTextChanged,
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            placeholder = { Text(text = "Search Here ...", color = Color.Gray) },
            leadingIcon = { SearchIcon() },
            trailingIcon = {
                if (searchText.isNotEmpty()) {
                    ClearIcon {
                        viewModel.onSearchTextChanged("")
                    }
                }
            },
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        ElevatedButton(shape = RoundedCornerShape(16.dp), onClick = { onSearchClick() }) {
            Text(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                text = "Search",
                style = MaterialTheme.typography.titleLarge
            )
        }

        LazyColumn(
            modifier = Modifier,
            contentPadding = PaddingValues(4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
//            item {
//                Text(
//                    modifier = Modifier.align(Alignment.Start),
//                    text = "Users",
//                    style = MaterialTheme.typography.bodyMedium
//                )
//            }
//            items(userList.value) { user ->
//                UserCard(user) {
//                    onUserClick(user)
//                }
//            }

            items(users) { user ->
                user?.let {
                    UserCard(it) {
                        onUserClick(it)
                    }
                }
            }

//            when (userList.loadState.append) {
//                LoadState.Loading -> item { LoadingItem() }
//                is LoadState.Error -> item {
//                    Popup() {
//                        Text(text = "Item not found")
//                    }
//                }
//                is LoadState.NotLoading -> Unit
//            }

//            when(userList.loadState.refresh){
//                LoadState.Loading -> item { Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){ CircularProgressIndicator()} }
//                is LoadState.Error ->item { Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){ Text(
//                    text = "Item not found"
//                )} }
//                is LoadState.NotLoading -> Unit
//            }


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

@Composable
fun UserCard(user: User, onUserClick: () -> Unit) {

    Spacer(modifier = Modifier.padding(vertical = 8.dp))

    ElevatedCard(
        Modifier
            .fillMaxWidth()
            .heightIn(max = 120.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),

        ) {
        Row(modifier = Modifier.clickable { onUserClick() }, verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                model = user.profilePicture,
                contentDescription = null
            )
            Spacer(modifier = Modifier.padding(horizontal = 16.dp))
            Column() {
                Text(text = user.username, style = MaterialTheme.typography.titleLarge)
                Text(text = AnnotatedString(user.profileURL), style = MaterialTheme.typography.bodySmall)
            }
        }
    }

    Spacer(modifier = Modifier.padding(bottom = 12.dp))

}

@Preview
@Composable
fun PreviewScreenA() {
//    ScreenAContent() {}
}