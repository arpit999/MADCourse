package com.example.madcourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.madcourse.ui.theme.MADCourseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MADCourseTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val clickCounter by remember { mutableStateOf("CLICK COUNTER") }
    // A surface container using the 'background' color from the theme
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        AppContent(clickCounter)
    }
}


@Composable
fun AppContent(clickCounter: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = clickCounter)
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Button(
            border = BorderStroke(3.dp, Color.Blue.copy(alpha = 0.5f)),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "BUTTON", fontWeight = FontWeight.Medium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MADCourseTheme {
        MyApp()
    }
}