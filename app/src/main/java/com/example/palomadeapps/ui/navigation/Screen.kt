package com.example.palomadeapps.ui.navigation

sealed class Screen (val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Camera : Screen("Camera")
    object Login : Screen("login")
    object Register : Screen("register")
    object Onboarding : Screen("onBoarding")
    object Track : Screen("track")
    object FAQ : Screen("faq")
    object DetailShip : Screen("DetailShip")

    object DetailReward : Screen("home/{rewardId}"){
        fun createRoute(rewardId: Long) = "home/$rewardId"
    }
}