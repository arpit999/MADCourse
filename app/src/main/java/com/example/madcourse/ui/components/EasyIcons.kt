package com.example.madcourse.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BackIcon(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    Icon(
        modifier = modifier
            .clickable { onBackClick() }
            .padding(12.dp),
        imageVector = Icons.Filled.ArrowBack,
        contentDescription = "Back"
    )
}

@Composable
fun ClearIcon(onClear: () -> Unit) {
    Icon(
        modifier = Modifier.clickable { onClear() },
        imageVector = Icons.Filled.Clear,
        contentDescription = "Back"
    )
}

@Composable
fun HomeIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Filled.Home,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primary
    )
}

