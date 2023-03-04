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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.madcourse.ui.theme.MADCourseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MADCourseTheme {
                CounterApp()
            }
        }
    }
}

@Composable
fun CounterApp() {
    var counter by remember { mutableStateOf(0) }
    val lifecycleOwner = LocalLifecycleOwner.current

    /**
     * Heart of this program. It will identify lifeCycle events within Composable and reset counter on OnStop lifecycle event
     */
    LaunchedEffect(lifecycleOwner.lifecycle) {
        lifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onStop(owner: LifecycleOwner) {
                counter = 0
            }
        })
    }

    // A surface container using the 'background' color from the theme
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        CounterAppContent(counter) {
            counter++
        }
    }
}


@Composable
fun CounterAppContent(clickCounter: Int, onButtonClicked: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = " Click count $clickCounter")
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Button(
            border = BorderStroke(3.dp, Color.Blue.copy(alpha = 0.5f)),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            onClick = { onButtonClicked() }
        ) {
            Text(text = "BUTTON")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MADCourseTheme {
        CounterApp()
    }
}