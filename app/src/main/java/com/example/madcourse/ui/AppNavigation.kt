package com.example.madcourse.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.madcourse.data.users
import com.example.madcourse.domain.UserViewModel

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: UserViewModel = viewModel(),
    startDestination: String = AppNavigation.SCREEN_A.name
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
            ScreenB(viewModel, navController)
        }
        composable(AppNavigation.SCREEN_C.name + "/{tableId}", arguments = listOf(
            navArgument("tableId") {
                type = NavType.IntType
                defaultValue = 1
            }
        )) { entry ->
            val user = entry.arguments?.getInt("tableId", 1)
            if (user != null) {
                viewModel.getUser(user)
            }
            ScreenC(navController,viewModel.userDetails.collectAsState(users.first()))
        }

    }
}

//fun withArgs(vararg args: String):String{
//    return buildString {
//        append(route)
//    }
//}

enum class AppNavigation {
    SCREEN_A,
    SCREEN_B,
    SCREEN_C
}