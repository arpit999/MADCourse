package com.example.madcourse.ui

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.madcourse.domain.UserViewModel
import com.example.madcourse.domain.network.model.UserDetail
import com.example.madcourse.domain.network.utils.Constant
import com.example.madcourse.domain.network.utils.NetworkResult

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
            val id = backStackEntry.arguments?.getString(Constant.id) ?: ""
            Log.d("TAG", "Id: $id")
            Log.d("TAG", "pagingItems: ${pagingItems.itemCount}")

//            val index =
//                pagingItems.itemSnapshotList.indexOfFirst { it?.id.contentEquals(id)  } // Very important otherwise we will get wrong item from active paginated list.
//            val user = pagingItems[index]

//            Log.d("TAG", "Post: $user")

            val userDetail = viewModel.userDetail.collectAsStateWithLifecycle()

            if (userDetail.value is NetworkResult.Success) {
                ScreenB((userDetail.value as NetworkResult.Success<UserDetail>).result, navController)
            } else {
                Text(text = "Item not found")
            }
        }

    }
}

sealed class AppNavigation(val route: String) {
    object ScreenA : AppNavigation(route = "HOME")
    object ScreenB : AppNavigation(route = "DETAILS/{${Constant.id}}") {
        fun passId(id: String): String {
            return this.route.replace(oldValue = "{${Constant.id}}", newValue = id)
        }
    }
}