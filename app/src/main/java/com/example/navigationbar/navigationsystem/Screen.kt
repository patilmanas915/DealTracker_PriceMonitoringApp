package com.example.navigationbar.navigationsystem

sealed class Screen(val route:String){
    object S1: Screen("Home")
    object S2: Screen("Search")
    object S3: Screen("Tracking")
    object S4: Screen("product")

}