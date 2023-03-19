package com.example.madcourse.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.madcourse.R
import com.example.madcourse.data.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenC(user: State<User>) {


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Surface(shadowElevation = 3.dp) {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.user_directory)) },
                    colors = TopAppBarDefaults.topAppBarColors()
                )
            }
        }
    ) { paddingValues ->

        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                textAlign = TextAlign.Center,
                text = "Welcome to ${user.value.email} list screen",
                style = MaterialTheme.typography.titleLarge
            )
        }

    }

}

