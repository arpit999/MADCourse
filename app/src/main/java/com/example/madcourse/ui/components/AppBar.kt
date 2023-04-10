package com.example.madcourse.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon: @Composable () -> Unit = {},
    action: @Composable () -> Unit = {}
) {
    ElevatedCard(shape = RectangleShape,elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
        TopAppBar(
            modifier = modifier,
            title = { Text(text = title) },
            navigationIcon = { navigationIcon() },
            actions = { action() })
    }
}

@Composable
fun AppBarWithTitle(title: String) {
    AppBar(title = title)
}

@Composable
fun AppBarWithBackIcon(title: String, onBackClick: () -> Unit) {
    AppBar(title = title, navigationIcon = {
        BackIcon() {
            onBackClick()
        }
    })
}