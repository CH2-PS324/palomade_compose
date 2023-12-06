package com.example.palomadeapps.views.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.palomadeapps.ui.navigation.Screen
import com.example.palomadeapps.ui.screen.login.LoginScreen
import com.example.palomadeapps.ui.screen.register.RegisterScreen
import com.example.palomadeapps.ui.screen.welcome.OnBoardingScreen
import com.example.palomadeapps.views.main.MainViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AppNavigation() {
//    val navController = rememberNavController()
//
//
//    NavHost(
//        navController = navController,
//        startDestination = Screen.Login.route,
//    ) {
//        composable(Screen.Onboarding.route){
//            OnBoardingScreen(
//                onButtonClick = {navController.navigate(Screen.Login.route)}
//            )
//        }
//        composable(Screen.Login.route) {
//            // Your main composable function
//            LoginScreen(navigate = navController)
//        }
//        composable(Screen.Register.route) {
//            // Composable function for registration screen
//            RegisterScreen(navigate = navController)
//
//        }
//        // Add more destinations as needed
//    }
}