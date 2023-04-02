package com.example.madcourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.madcourse.ui.AppNavigation
import com.example.madcourse.ui.MyAppNavHost
import com.example.madcourse.ui.theme.MADCourseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MADCourseTheme {
                MyAppNavHost(startDestination = AppNavigation.ScreenA.route)
            }
        }

    }
}