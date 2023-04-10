package com.example.madcourse.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.madcourse.domain.network.model.*
import com.example.madcourse.ui.components.BackIcon
import com.example.madcourse.ui.components.HyperlinkText
import com.example.madcourse.ui.components.RectangleImage
import com.example.madcourse.ui.components.VerticalSpacer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenB(userDetail: UserDetail, navController: NavHostController) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Surface(shadowElevation = 3.dp) {
                TopAppBar(
                    title = { Text(text = "Post Details") },
                    navigationIcon = {
                        BackIcon {
                            navController.popBackStack()
                        }
                    }
                )
            }
        },
    ) { paddingValues ->

        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(16.dp),
            elevation = CardDefaults.elevatedCardElevation(4.dp)
        ) {

            RectangleImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                url = userDetail.profilePicture,
            ) {}

            VerticalSpacer(size = 8)

            Column(Modifier.padding(horizontal = 16.dp, vertical = 20.dp)) {
                Text(
                    text = "POST : ${userDetail.id}",
                    style = MaterialTheme.typography.labelLarge, color = Color.Black, fontWeight = FontWeight.Bold
                )
                Divider()
                VerticalSpacer(size = 4)
                Text(
                    text = "Author: ${userDetail.name}",
                    style = MaterialTheme.typography.labelLarge
                )

                Text(
                    text = "About: ${userDetail.about}",
                    style = MaterialTheme.typography.labelLarge,
                )

                HyperlinkText(
                    fullText = "Profile Picture: " + userDetail.profilePicture,
                    linkMap = mapOf(userDetail.profilePicture to userDetail.profilePicture),
                    linkFontStyle = MaterialTheme.typography.labelLarge.fontStyle,
                    linkTextDecoration = TextDecoration.Underline,
                    linkTextColor = Color.Blue
                )
            }

        }
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
