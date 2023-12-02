package com.example.palomadeapps

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.palomadeapps.ui.navigation.NavigationItem
import com.example.palomadeapps.ui.navigation.Screen
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.*
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.palomadeapps.ui.screen.auth.login.LoginScreen
import com.example.palomadeapps.ui.screen.auth.register.RegisterScreen
import com.example.palomadeapps.ui.screen.camera.CameraScreen
import com.example.palomadeapps.ui.screen.home.HomeScreen
import com.example.palomadeapps.ui.screen.profile.ProfileScreen
import com.example.palomadeapps.ui.screen.welcome.OnBoarding
import com.example.palomadeapps.ui.theme.PalomadeAppsTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun PalomadeApp (
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar =  {
            if (
                currentRoute != Screen.DetailReward.route &&
                currentRoute != Screen.Onboarding.route &&
                currentRoute != Screen.Login.route &&
                currentRoute != Screen.Register.route
                ){
                BottomBar(navController)
            }
        },
        modifier = modifier
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Onboarding.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Onboarding.route){
                OnBoarding(
                    onButtonClick = {navController.navigate(Screen.Login.route)}
                )
            }
            composable(Screen.Register.route){
                RegisterScreen(navigate = navController)
            }

            composable(Screen.Login.route){
                LoginScreen(navigate = navController)
            }
            composable(Screen.Home.route){
                HomeScreen()
            }
            composable(Screen.Inventory.route) {
                CameraScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavigationBar (
        modifier = modifier
            .graphicsLayer {
                shape = RoundedCornerShape(
                    topStart = 22.dp,
                    topEnd = 22.dp
                )
                clip = true
            }
        //        containerColor = colorResource(id = R.color.black)
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = ImageVector.vectorResource(id = R.drawable.ic_gome),
                screen = Screen.Home,
            ),
            NavigationItem(
                title = stringResource(R.string.menu_cam),
                icon = ImageVector.vectorResource(id = R.drawable.ic_cameraa),
                screen = Screen.Inventory
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = ImageVector.vectorResource(id = R.drawable.ic_account),
                screen = Screen.Profile
            ),
        )

        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = colorResource(id = R.color.black)
                    )
                },

                label = { Text(item.title, color = colorResource(id = R.color.black)) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }

    }
}
@Preview(showBackground = true)
@Composable
fun JetHeroesAppPreview() {
    PalomadeAppsTheme {
        PalomadeApp()
    }
}