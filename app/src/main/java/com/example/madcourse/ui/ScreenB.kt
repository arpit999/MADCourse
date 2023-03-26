package com.example.madcourse.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
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
                        BackIcon(Modifier.padding(8.dp)) {
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

    val context = LocalContext.current

    Column(modifier = modifier.fillMaxSize()) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            textAlign = TextAlign.Center,
            text = "Welcome to user detail screen",
            style = MaterialTheme.typography.titleLarge
        )

        Text(text = ("Full Name:" + userDetails.value?.fullName), style = MaterialTheme.typography.titleLarge)

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {

            ElevatedButton(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                onClick = { }) {
                Text(
                    text = "CLick here to add user",
                    style = MaterialTheme.typography.titleLarge
                )
            }

        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserItem(modifier: Modifier = Modifier, userDetails: State<UserDetails>) {

    ElevatedCard(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .padding(top = 8.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
//            Text(modifier = Modifier, text = stringResource(id = R.string.user_id) + " ${user.userId}")
//            Text(modifier = Modifier, text = stringResource(id = R.string.username) + " ${user.username}")
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .padding(bottom = 8.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.Start) {
//                Text(text = stringResource(id = R.string.full_name) + " ${user.fullName}")
//                Text(text = stringResource(id = R.string.email) + " ${user.email}")
            }

            Box(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .background(color = Color.Black, shape = CircleShape)
                    .padding(3.dp)
            ) {
                Badge(
                    contentColor = Color.Black,
                    containerColor = Color.White
                ) {
//                    Text(
//                        modifier = Modifier
//                            .padding(4.dp),
////                        text = "${index + 1}",
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 14.sp
//                    )
                }
            }

        }

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