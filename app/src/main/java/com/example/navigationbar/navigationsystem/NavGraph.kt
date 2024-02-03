package com.example.navigationbar.navigationsystem

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.navigationbar.appscreens.Screen1
import com.example.navigationbar.appscreens.Screen3
import com.example.navigationbar.appscreens.Screen3ViewModel
import com.example.navigationbar.appscreens.Screen4
import com.example.navigationbar.appscreens.Screen4ViewModel
import com.example.navigationbar.appscreens.Screen5
import com.example.navigationbar.appscreens.WebViewScreen

@Composable
fun SetupNavigation(
    navHostController: NavHostController,
    modifier: Modifier,
    viewModel:Screen4ViewModel,
    viewModel1: Screen3ViewModel
) {
    NavHost(navController = navHostController, startDestination = Screen.S1.route) {
        composable(route = Screen.S1.route) {
            Screen1(Modifier, navHostController)
        }
        composable(route = Screen.S2.route) {
            Screen5(Modifier, navHostController)
        }
        composable(route = Screen.S3.route) {
            Screen3(Modifier, navHostController,viewModel1)
        }
        composable("product") {
            Screen4(modifier = Modifier, navHostController = navHostController,url ="SMSSPRODUCT", screen4ViewModel =viewModel )

        }
        composable(
            route = "product/{my_param}",
            arguments = listOf(
                navArgument("my_param") {
                    type = NavType.StringType
                }
            )
        ) {
            val param = it.arguments?.getString("my_param") ?: ""
            Screen4(modifier = Modifier, navHostController = navHostController,url = param, screen4ViewModel =viewModel )
        }
        composable(route = "web1") {
            WebViewScreen(navHostController, "https://www.amazon.in",viewModel)
        }
        composable(route = "web2") {
            WebViewScreen(
                navHostController,
                "https://www.flipkart.com/",
                viewModel
            )
        }
        composable(
            route = "sch/{my_param}",
            arguments = listOf(
                navArgument("my_param") {
                    type = NavType.StringType
                }
            )
        ) {
            val param = it.arguments?.getString("my_param") ?: ""
            WebViewScreen(
                navHostController,
                "https://www.amazon.in/s?k=${param}",viewModel
            )

        }
    }
}


