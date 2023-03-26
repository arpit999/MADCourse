package com.example.madcourse.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.madcourse.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenC(navController: NavHostController) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Surface(shadowElevation = 3.dp) {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.user_details)) },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(Icons.Default.ArrowBack, "Back")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors()
                )
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                textAlign = TextAlign.Center,
                text = "Welcome to user detail screen",
                style = MaterialTheme.typography.titleLarge
            )

            ElevatedCard(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .fillMaxWidth()
            ) {

//                RowContent(value = "User Id: ${user.value.userId}")
//                RowContent(value = "Username: ${user.value.username}")
//                RowContent(value = "Full Name: ${user.value.fullName}")
//                RowContent(value = "Email: ${user.value.email}")

            }
        }

    }

}

@Composable
fun RowContent(modifier: Modifier = Modifier, value: String) {
    Row(modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
            text = value,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
