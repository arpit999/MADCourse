package com.example.madcourse.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.madcourse.R
import com.example.madcourse.domain.UserViewModel
import com.example.madcourse.domain.network.model.*
import com.example.madcourse.ui.components.HorizontalSpacer
import com.example.madcourse.ui.components.VerticalSpacer

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenA(viewModel: UserViewModel, navController: NavHostController) {

    val profile by viewModel.userProfile.collectAsState()

    Scaffold(topBar = {
        Surface(shadowElevation = 3.dp) {
            TopAppBar(title = { Text(text = "User Directory") })
        }
    }) { paddingValues ->

        ProfileDetails(Modifier.padding(paddingValues), profile = profile)

    }
}


@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(42.dp)
                .height(42.dp)
                .padding(8.dp),
            strokeWidth = 5.dp
        )

    }
}


@Composable
fun ProfileDetails(modifier: Modifier = Modifier, profile: Profile?) {

    Column(
        modifier = modifier
            .padding(12.dp)
    ) {
        Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .border(border = BorderStroke(2.dp, Color.Black), CircleShape),
                model = profile?.picture,
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null
            )

            HorizontalSpacer(size = 16)

            Column {
                profile?.let { profile ->
                    Text(text = profile.getFullName(), style = MaterialTheme.typography.titleLarge)
                    Text(
                        text = profile.employment.title,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray
                    )
                    Text(text = profile.email, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                }
            }
        }


        Column(Modifier.padding(vertical = 12.dp, horizontal = 8.dp)) {
            Text(text = "Address", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
            Divider()
            profile?.address?.let { address ->
                VerticalSpacer(size = 4)
                Text(
                    text = address.streetName + " " + address.streetAddress,
                    style = MaterialTheme.typography.bodyLarge, color = Color.Gray
                )
                Text(
                    text = address.zipCode + ", " + address.city + " " + address.state,
                    style = MaterialTheme.typography.bodyLarge, color = Color.Gray
                )
                Text(text = address.country, style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
            }
        }

        //SUBSCRIPTION
        Column(Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
            Text(text = "Subscription", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
            Divider()
            profile?.subscription?.let { subscription ->
                VerticalSpacer(size = 4)
                Text(
                    text = subscription.plan + " (" + subscription.term + ")",
                    style = MaterialTheme.typography.bodyLarge, color = Color.Gray
                )
                Text(
                    text = "Payment Method " + subscription.paymentMethod,
                    style = MaterialTheme.typography.bodyLarge, color = Color.Gray
                )
            }
        }


    }
}


@Composable
fun UserCard(user: User, onUserClick: () -> Unit) {

    ElevatedCard(
        Modifier
            .fillMaxWidth()
            .heightIn(max = 130.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
    )
    {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 8.dp)
                .clickable { onUserClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                model = user.profilePicture,
                contentDescription = null
            )
            Spacer(modifier = Modifier.padding(horizontal = 16.dp))
            Column() {
                Text(text = user.username, style = MaterialTheme.typography.titleLarge)
                Text(text = AnnotatedString(user.profileURL), style = MaterialTheme.typography.bodySmall)
            }
        }
    }

    VerticalSpacer(size = 12)

}

@Preview
@Composable
fun PreviewScreenA() {
    ProfileDetails(profile = profile)
}