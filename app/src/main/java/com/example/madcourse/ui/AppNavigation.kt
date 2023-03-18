package com.example.madcourse.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.madcourse.domain.UserViewModel

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    startDestination: String = AppNavigation.SCREEN_B.name
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppNavigation.SCREEN_A.name) {
            ScreenA(viewModel)
        }
        composable(AppNavigation.SCREEN_B.name) {
            ScreenB(viewModel)
        }
        composable(AppNavigation.SCREEN_C.name) { }
    }
}

enum class AppNavigation {
    SCREEN_A,
    SCREEN_B,
    SCREEN_C
}