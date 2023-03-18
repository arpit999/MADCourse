package com.example.madcourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.madcourse.domain.UserViewModel
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

                val viewModel: UserViewModel by viewModels()
                val isUserAdded by viewModel.isUserAdded.collectAsStateWithLifecycle()

                MyAppNavHost(
                    viewModel = viewModel,
                    startDestination = if (isUserAdded) AppNavigation.SCREEN_B.name else AppNavigation.SCREEN_A.name
                )
            }
        }
    }
}