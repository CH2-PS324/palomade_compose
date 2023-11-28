package com.example.palomadeapps.ui.navigation

sealed class Screen (val route: String) {
    object Home : Screen("home")
    object Inventory : Screen("Scan")
    object Profile : Screen("profile")

    object DetailReward : Screen("home/{rewardId}"){
        fun createRoute(rewardId: Long) = "home/$rewardId"
    }
}