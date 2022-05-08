package com.example.secure_user_authentication.navigation

sealed class Screen(val route: String){

    object Login: Screen(route = "login_screen")
    object Profile: Screen(route = "profile_screen")
}
