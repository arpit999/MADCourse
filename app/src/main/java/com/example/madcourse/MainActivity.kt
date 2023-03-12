@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.madcourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.madcourse.model.User
import com.example.madcourse.model.users
import com.example.madcourse.ui.theme.MADCourseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MADCourseTheme {
                MyApp()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Surface(shadowElevation = 3.dp) {
                SmallTopAppBar(
                    title = { Text(text = stringResource(id = R.string.users)) },
                    colors = TopAppBarDefaults.smallTopAppBarColors()
                )
            }
        }
    ) { paddingValues ->

        LazyColumn(Modifier.padding(paddingValues)) {
            itemsIndexed(users) { index, user ->
                UserItem(user = user, index = index)
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserItem(modifier: Modifier = Modifier, user: User, index: Int) {

    ElevatedCard(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(modifier = Modifier, text = stringResource(id = R.string.user_id) + " ${user.userId}")
            Text(modifier = Modifier, text = stringResource(id = R.string.username) + " ${user.username}")
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(modifier = Modifier.padding(horizontal = 8.dp), horizontalAlignment = Alignment.Start) {
                Text(text = stringResource(id = R.string.full_name) + " ${user.fullName}")
                Text(text = stringResource(id = R.string.email) + " ${user.email}")
                Text(text = stringResource(id = R.string.username) + " ${user.username}")
            }

            Box(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .background(color = Color.Black, shape = CircleShape)
                    .padding(3.dp)
            ) {
                Badge(
                    contentColor = Color.Black,
                    containerColor = Color.White
                ) {
                    Text(
                        modifier = Modifier
                            .padding(4.dp),
                        text = "${index + 1}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }

        }


    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MADCourseTheme {
        MyApp()
    }
}