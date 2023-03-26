package com.example.madcourse.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.madcourse.R
import com.example.madcourse.domain.UserViewModel
import com.example.madcourse.domain.network.model.UserDetails
import com.example.madcourse.ui.components.BackIcon
import com.example.madcourse.ui.theme.MADCourseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenB(viewModel: UserViewModel, navController: NavHostController) {

    val userDetails = viewModel.userDetails.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Surface(shadowElevation = 3.dp) {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.user_details)) },
                    navigationIcon = {
                        BackIcon {
                            navController.popBackStack()
                        }
                    }
                )
            }
        },
    ) { paddingValues ->

        Log.d("TAG", "ScreenB: $userDetails")
        ScreenBContent(
            Modifier.padding(paddingValues),
            userDetails
        )

    }
}

@Composable
fun ScreenBContent(
    modifier: Modifier = Modifier,
    userDetails: State<UserDetails?>,
) {

    Column(modifier = modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            textAlign = TextAlign.Center,
            text = "Welcome to user detail screen",
            style = MaterialTheme.typography.titleLarge
        )
        userDetails.value?.let { it -> UserDetailCard(it) }
    }
}

@Composable
fun UserDetailCard(user: UserDetails) {
    ElevatedCard(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),

        ) {

        Column(
            Modifier.padding(vertical = 16.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    modifier = Modifier
                        .size(130.dp)
                        .clip(CircleShape),
                    model = user.profilePic,
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.padding(horizontal = 16.dp))

            user.fullName?.let { Text(text = it, style = MaterialTheme.typography.titleLarge) }
            Text(text = user.userName, style = MaterialTheme.typography.bodyLarge)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StatisticsBlock(label = "Followers", value = user.followers)
                Divider(color = Color.Black, modifier = Modifier
                    .height(40.dp)
                    .width(2.dp))
                StatisticsBlock(label = "Repository", value = user.repositories)
                Divider(color = Color.Black, modifier = Modifier
                    .height(40.dp)
                    .width(2.dp))
                StatisticsBlock(label = "Following", value = user.following)
            }
            Spacer(modifier = Modifier.padding(vertical = 16.dp))
            user.biography?.let { Text(text = it) }
            Spacer(modifier = Modifier.padding(vertical = 16.dp))

            Text(text = user.createAt())
        }

    }
}

@Composable
fun StatisticsBlock(label: String, value: String) {
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, style = MaterialTheme.typography.headlineLarge)
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
//    val userList = listOf(
//        User(112, 1234, "Arpit003", " Arpit Patel", "arpit@gmail.com"),
//        User(112, 1234, "Arpit003", " Arpit Patel", "arpit@gmail.com"),
//        User(112, 1234, "Arpit003", " Arpit Patel", "arpit@gmail.com"),
//        User(112, 1234, "Arpit003", " Arpit Patel", "arpit@gmail.com")
//    )
    MADCourseTheme {
//        ScreenBContent(userListState = State<User>(userList) )
    }
}