package com.example.madcourse.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.madcourse.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String = stringResource(id = R.string.app_name), icon: ImageVector? = Icons.Filled.ArrowBack) {
    Surface(shadowElevation = 3.dp) {
        TopAppBar(title = { Text(text = title) }, actions = {
            BackIcon {

            }
        })
    }
}

@Composable
fun BackIcon(onBackClick: () -> Unit) {
    Icon(
        modifier = Modifier.clickable { onBackClick() },
        imageVector = Icons.Filled.ArrowBack,
        contentDescription = "Back"
    )
}

