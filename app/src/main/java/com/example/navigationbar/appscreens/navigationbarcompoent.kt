package com.example.navigationbar.appscreens

import android.net.Uri
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.navigationbar.navigationsystem.Screen

@Composable
fun NavBar(modifier: Modifier = Modifier, navHostController: NavHostController) {
  Card(
        modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp,
        )
    ) {

        Row() {
            IconButton(
                onClick = { navHostController.navigate(Screen.S1.route) },
                Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null,
                    modifier
                        .size(50.dp)
                )

            }
            IconButton(
                onClick = { navHostController.navigate(Screen.S2.route) },
                Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier
                        .weight(1f)
                        .size(50.dp)
                )

            }
            IconButton(
                onClick = {
                    navHostController.navigate(Screen.S3.route)
                },
                Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = null,
                    modifier
                        .weight(1f)
                        .size(50.dp)
                )

            }
        }
    }
}