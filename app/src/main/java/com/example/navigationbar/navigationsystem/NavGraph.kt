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
import com.example.navigationbar.appscreens.Screen4
import com.example.navigationbar.appscreens.Screen4ViewModel
import com.example.navigationbar.appscreens.Screen5
import com.example.navigationbar.appscreens.WebViewScreen

@Composable
fun SetupNavigation(
    navHostController: NavHostController,
    modifier: Modifier,
    viewModel:Screen4ViewModel
) {
    NavHost(navController = navHostController, startDestination = Screen.S1.route) {
        composable(route = Screen.S1.route) {
            Screen1(Modifier, navHostController)
        }
        composable(route = Screen.S2.route) {
            Screen5(Modifier, navHostController)
        }
        composable(route = Screen.S3.route) {
            Screen3(Modifier, navHostController)
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
            WebViewScreen(navHostController, "https://www.amazon.in")
        }
        composable(route = "web2") {
            WebViewScreen(
                navHostController,
                "https://www.flipkart.com/?affid=affveve&affExtParam1=13bdae52e5586f9f690c4763bde90aa9&affExtParam2=99399"
            )
        }

    }
}


