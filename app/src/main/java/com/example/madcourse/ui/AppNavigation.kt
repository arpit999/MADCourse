package com.example.madcourse.ui

import android.util.Log
import androidx.compose.material3.Text
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
    val pagingItems = remember {
        viewModel.getPaginatedPosts()
    }.collectAsLazyPagingItems()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppNavigation.ScreenA.route) {

            val profile by viewModel.profileDetails.collectAsState()
//            val posts = viewModel.posts

            ScreenA(profile, pagingItems) {
                Log.d("TAG", "ScreenA click id : ${it.id}")
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
            Log.d("TAG", "pagingItems: ${pagingItems.itemCount}")

            val index =
                pagingItems.itemSnapshotList.indexOfFirst { it?.id == id } // Very important otherwise we will get wrong item from active paginated list.
            val post = pagingItems[index]

            Log.d("TAG", "Post: $post")

            if (post != null) {
                ScreenB(post, navController)
            } else {
                Text(text = "Item not found")
            }
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