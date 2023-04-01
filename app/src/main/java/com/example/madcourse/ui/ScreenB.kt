package com.example.madcourse.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.madcourse.R
import com.example.madcourse.domain.UserViewModel
import com.example.madcourse.domain.network.model.Address
import com.example.madcourse.domain.network.model.Employment
import com.example.madcourse.domain.network.model.Profile
import com.example.madcourse.domain.network.model.Subscription
import com.example.madcourse.ui.components.BackIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenB(viewModel: UserViewModel, navController: NavHostController) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Surface(shadowElevation = 3.dp) {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.user_details)) },
                    navigationIcon = {
                        BackIcon {
                            navController.popBackStack()
                        }
                    }
                )
            }
        },
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(vertical = 16.dp)
        ) {
//            userDetails.value?.let { it -> UserDetailCard(it) }
        }
    }
}


@Composable
fun StatisticsBlock(label: String, value: String) {
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, style = MaterialTheme.typography.headlineLarge)
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview
@Composable
fun PreviewProfileCard() {

    val profile = Profile(
        firstName = "Alice",
        lastName = "Jon",
        username = "Doe",
        employment = Employment(title = "Administrator"),
        email = "",
        address = Address(
            streetName = "57 Forest AVe",
            city = "Hamilton",
            streetAddress = "",
            zipCode = "L8K 0A3",
            state = "Gujarat",
            country = "Canada"
        ),
        picture = "",
        subscription = Subscription(plan = "Premium", status = "Idle", term = "Monthly", "Paypal")
    )

    ProfileDetails(profile = profile)
}
