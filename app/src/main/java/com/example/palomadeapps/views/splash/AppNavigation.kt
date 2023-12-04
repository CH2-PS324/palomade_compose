package com.example.palomadeapps.views.splash

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.palomadeapps.ui.navigation.Screen
import com.example.palomadeapps.ui.screen.login.LoginScreen
import com.example.palomadeapps.ui.screen.register.RegisterScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Onboarding.route,
    ) {
        composable(Screen.Login.route) {
            // Your main composable function
            LoginScreen(navigate = navController)
        }
        composable(Screen.Register.route) {
            // Composable function for registration screen
            RegisterScreen(navigate = navController)

        }
        // Add more destinations as needed
    }
}