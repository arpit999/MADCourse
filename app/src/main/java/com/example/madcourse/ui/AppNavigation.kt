package com.example.madcourse.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.madcourse.domain.UserViewModel

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppNavigation.SCREEN_A.name
) {

    val viewModel: UserViewModel = viewModel()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppNavigation.SCREEN_A.name) {

            val profile by viewModel.profileDetails.collectAsState()
            val posts = viewModel.posts

            ScreenA(profile, posts) {
                // TODO go to detail screen
            }

        }
        composable(AppNavigation.SCREEN_B.name + "/{username}", arguments = listOf(
            navArgument("username") {
                type = NavType.StringType
                defaultValue = ""
            }
        )) {
            ScreenB(viewModel, navController)
        }

    }
}


enum class AppNavigation {
    SCREEN_A,
    SCREEN_B,
}