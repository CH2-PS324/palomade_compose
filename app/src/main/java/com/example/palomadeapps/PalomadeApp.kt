package com.example.palomadeapps

import androidx.activity.ComponentActivity
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.palomadeapps.data.di.Injection
import com.example.palomadeapps.ui.screen.FAQ.FAQScreen
import com.example.palomadeapps.ui.screen.Shipping.Shipping2
import com.example.palomadeapps.ui.screen.Shipping.ShippingScreen
import com.example.palomadeapps.ui.screen.camera.CameraScreen3
import com.example.palomadeapps.ui.screen.detail.DetailScreen
import com.example.palomadeapps.ui.screen.detailShipping.DetailShipScreen
import com.example.palomadeapps.ui.screen.login.LoginScreen
import com.example.palomadeapps.ui.screen.register.RegisterScreen
import com.example.palomadeapps.ui.screen.home.HomeScreen
import com.example.palomadeapps.ui.screen.profile.ProfileScreen
import com.example.palomadeapps.ui.screen.track.TrackScreen
import com.example.palomadeapps.ui.screen.welcome.OnBoardingScreen
import com.example.palomadeapps.views.main.MainViewModel
import com.google.accompanist.pager.ExperimentalPagerApi


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun PalomadeApp (
    activity: ComponentActivity,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    )
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar =  {
            if (
                currentRoute != Screen.DetailReward.route &&
                currentRoute != Screen.Onboarding.route &&
                currentRoute != Screen.Login.route &&
                currentRoute != Screen.Register.route &&
                currentRoute != Screen.FAQ.route &&
                currentRoute != Screen.Shipping.route &&
                currentRoute != Screen.Shipping2.route &&
                currentRoute != Screen.DetailShip.route
                ){
                BottomBar(navController)
            }
        },
        modifier = modifier
    ){ innerPadding ->
        val uiLoginState by viewModel.uiLoginState.collectAsState()

        val startDestination = remember {
            if (uiLoginState?.token?.isNotBlank()  == true){
                Screen.Home.route
            }else {
                Screen.Onboarding.route
            }
        }

        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Login.route){
                LoginScreen(navigate = navController)
            }
            
            composable(Screen.Onboarding.route){
                OnBoardingScreen(
                    onButtonClick = {navController.navigate(Screen.Login.route)}
                )
            }
            composable(Screen.Register.route) {
            // Composable function for registration screen
            RegisterScreen(navigate = navController)
            }

            composable(Screen.FAQ.route){
                FAQScreen(navigate = navController)
            }

            composable(Screen.DetailShip.route){
                DetailShipScreen(navigate = navController)
            }

            composable(Screen.Home.route){
                HomeScreen(navigateToDetail = { rewardId ->
                    navController.navigate(Screen.DetailReward.createRoute(rewardId))
                })
            }

            composable(Screen.Track.route){
                TrackScreen(navigate = navController)
            }

            composable(Screen.Shipping.route){
                ShippingScreen(navigate = navController)
            }

            composable(Screen.Shipping2.route){
                Shipping2(navigate = navController)
            }

            composable(Screen.Camera.route) {

//                CameraScreen(navigate = navController)
                CameraScreen3(navigate = navController)
            }
            composable(Screen.Profile.route) {
                ProfileScreen(activity = MainActivity(), navigate = navController)
            }
            composable(
                route = Screen.DetailReward.route,
                arguments = listOf(navArgument("rewardId") { type = NavType.LongType }),
            ) {
                val id = it.arguments?.getLong("rewardId") ?: -1L
                DetailScreen(
                    rewardId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavigationBar (
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color(0xFFD5D5D5),
            )
            .graphicsLayer {
                clip = true
            },
            containerColor = colorResource(id = R.color.white)
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                selectedIcon = ImageVector.vectorResource(id = R.drawable.ic_home),
                unselectedIcon = ImageVector.vectorResource(id = R.drawable.ic_home),
                hasNews = true,
                screen = Screen.Home,
            ),
            NavigationItem(
                title = stringResource(R.string.menu_cam),
                selectedIcon = ImageVector.vectorResource(id = R.drawable.ic_scan),
                unselectedIcon = ImageVector.vectorResource(id = R.drawable.ic_scan),
                hasNews = true,
                screen = Screen.Camera
            ),
            NavigationItem(
                title = stringResource(R.string.track),
                selectedIcon = ImageVector.vectorResource(id = R.drawable.shipping),
                unselectedIcon = ImageVector.vectorResource(id = R.drawable.shipping),
                hasNews = true,
                screen = Screen.Track
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                selectedIcon = ImageVector.vectorResource(id = R.drawable.ic_profile),
                unselectedIcon = ImageVector.vectorResource(id = R.drawable.ic_profile),
                hasNews = true,
                screen = Screen.Profile
            ),
        )
        var selectedItemIndex by rememberSaveable {
            mutableStateOf(0)
        }

        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex) {
                            item.selectedIcon
                        } else item.unselectedIcon,
                        contentDescription = item.title
                    )
                },

                alwaysShowLabel = false,
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    selectedItemIndex = index
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },

            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun JetHeroesAppPreview() {
//    PalomadeAppsTheme {
//        PalomadeApp()
//    }
//}