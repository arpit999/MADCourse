package com.example.madcourse.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

        ScreenAContent(Modifier.padding(paddingValues), userList = userList) {
            viewModel.getUsers("arpit", 1)
        }
    }
}

@Composable
fun ScreenAContent(modifier: Modifier = Modifier, userList: State<List<User>>, onUserClick: () -> Unit) {

    var searchQuary by remember {
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
            value = searchQuary,
            onValueChange = { newValue ->
                searchQuary = newValue
            },
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            placeholder = { Text(text = "Search Here ...", color = Color.Gray) },
            leadingIcon = { SearchIcon() },
            trailingIcon = {
                if (searchQuary.isNotEmpty()) {
                    ClearIcon {
                        searchQuary = ""
                    }
                }
            },
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        ElevatedButton(shape = RoundedCornerShape(16.dp), onClick = { onUserClick() }) {
            Text(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                text = "Add All Users",
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
    Column(Modifier.fillMaxWidth()) {
        Row() {
            AsyncImage(
//                modifier = Modifier.clip(CircleShape),
                model = user.profilePicture,
                contentDescription = null
            )

            Column {
                Text(text = user.username, style = MaterialTheme.typography.titleLarge)
                Text(text = AnnotatedString(user.profileURL), style = MaterialTheme.typography.bodySmall)
            }
        }
    }

}

@Preview
@Composable
fun PreviewScreenA() {
//    ScreenAContent() {}
}