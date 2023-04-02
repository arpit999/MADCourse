package com.example.madcourse.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.madcourse.domain.UserViewModel
import com.example.madcourse.domain.network.utils.Constant

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppNavigation.ScreenA.route
) {

    val viewModel: UserViewModel = viewModel()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppNavigation.ScreenA.route) {

            val profile by viewModel.profileDetails.collectAsState()
            val posts = viewModel.posts
            val pagingItems = remember {
                viewModel.getPaginatedPosts()
            }.collectAsLazyPagingItems()

            ScreenA(profile, pagingItems) {
                navController.navigate(AppNavigation.ScreenB.passId(it.id))
            }

        }
        composable(AppNavigation.ScreenB.route, arguments = listOf(
            navArgument("id") {
                type = NavType.IntType
            }
        )) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt(Constant.id) ?: -1
            Log.d("TAG", "Id: $id")
//            val post = viewModel.posts[id] TODO Question - It is crashing sometimes on post click says that  java.lang.IndexOutOfBoundsException: index: 34, size: 34
            val post = viewModel.posts[id] ?: viewModel.posts.first()

            ScreenB(post, navController)
        }

    }
}

sealed class AppNavigation(val route: String) {
    object ScreenA : AppNavigation(route = "HOME")
    object ScreenB : AppNavigation(route = "DETAILS/{${Constant.id}}") {
        fun passId(id: Int): String {
            return this.route.replace(oldValue = "{${Constant.id}}", newValue = "$id")
        }
    }
}