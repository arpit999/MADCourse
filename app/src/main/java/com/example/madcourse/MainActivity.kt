package com.example.madcourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.madcourse.domain.UserViewModel
import com.example.madcourse.ui.AppNavigation
import com.example.madcourse.ui.MyAppNavHost
import com.example.madcourse.ui.theme.MADCourseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenStarted {

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
            val isUserAdded by viewModel.isUserAdded.collectAsStateWithLifecycle(initialValue = false)
            val lifecycleOwner = LocalLifecycleOwner.current

            LaunchedEffect(key1 = lifecycleOwner.lifecycle, block = {
                lifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver{
                    override fun onStart(owner: LifecycleOwner) {
                        super.onStart(owner)

                    }
                })
            })

            MADCourseTheme {

                MyAppNavHost(
                    viewModel = viewModel,
                    startDestination = if (isUserAdded) AppNavigation.SCREEN_B.name else AppNavigation.SCREEN_A.name
                )
            }
        }
    }
}