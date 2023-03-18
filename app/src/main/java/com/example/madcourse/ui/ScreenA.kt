package com.example.madcourse.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.madcourse.domain.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenA(viewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    Scaffold(topBar = {
        Surface(shadowElevation = 3.dp) {
            TopAppBar(title = { Text(text = "User Directory") })
        }
    }) { paddingValues ->
        ScreenAContent(Modifier.padding(paddingValues)) {
            viewModel.upsertUsers()
        }
    }
}

@Composable
fun ScreenAContent(modifier: Modifier = Modifier, addAllUserClick: () -> Unit) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ElevatedButton(shape = RoundedCornerShape(16.dp), onClick = { addAllUserClick() }) {
            Text(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                text = "Add All Users",
                style = MaterialTheme.typography.titleLarge
            )
        }

    }

}

@Preview
@Composable
fun PreviewScreenA() {
    ScreenAContent(){}
}