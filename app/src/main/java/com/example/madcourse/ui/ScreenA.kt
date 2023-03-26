package com.example.madcourse.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import coil.compose.AsyncImage
import com.example.madcourse.domain.UserViewModel
import com.example.madcourse.domain.network.model.User
import com.example.madcourse.ui.components.ClearIcon
import com.example.madcourse.ui.components.SearchIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenA(viewModel: UserViewModel) {

    val userList = viewModel.userList.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        Surface(shadowElevation = 3.dp) {
            TopAppBar(title = { Text(text = "User Directory") })
        }
    }) { paddingValues ->

        ScreenAContent(Modifier.padding(paddingValues), userList = userList) { searchQuery ->
            viewModel.getUsers(searchQuery, 1)
        }
    }
}

@Composable
fun ScreenAContent(
    modifier: Modifier = Modifier,
    userList: State<List<User>>,
    onSearchClick: (String) -> Unit
) {

    var searchQuery by remember {
        mutableStateOf("")
    }

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
            value = searchQuery,
            onValueChange = { newValue ->
                searchQuery = newValue
            },
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            placeholder = { Text(text = "Search Here ...", color = Color.Gray) },
            leadingIcon = { SearchIcon() },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    ClearIcon {
                        searchQuery = ""
                    }
                }
            },
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        ElevatedButton(shape = RoundedCornerShape(16.dp), onClick = { onSearchClick(searchQuery) }) {
            Text(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                text = "Search",
                style = MaterialTheme.typography.titleLarge
            )
        }

        LazyColumn() {
            item {
                Text(
                    modifier = Modifier.align(Alignment.Start),
                    text = "Users",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            items(userList.value) { user ->
                UserCard(user)
            }
        }

    }

}


@Composable
fun UserCard(user: User) {

    Spacer(modifier = Modifier.padding(vertical = 8.dp))

    ElevatedCard(
        Modifier
            .fillMaxWidth()
            .heightIn(max = 120.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),

        ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
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