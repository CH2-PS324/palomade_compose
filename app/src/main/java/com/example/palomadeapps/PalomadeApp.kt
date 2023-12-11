package com.example.palomadeapps

import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.palomadeapps.data.di.Injection
import com.example.palomadeapps.ui.screen.camera.CameraScreen
import com.example.palomadeapps.ui.screen.camera.CameraScreen2
import com.example.palomadeapps.ui.screen.login.LoginScreen
import com.example.palomadeapps.ui.screen.register.RegisterScreen
import com.example.palomadeapps.ui.screen.home.HomeScreen
import com.example.palomadeapps.ui.screen.profile.ProfileScreen
import com.example.palomadeapps.ui.screen.scan.ScanScreen
import com.example.palomadeapps.ui.screen.track.TrackScreen
import com.example.palomadeapps.ui.screen.welcome.OnBoardingScreen
import com.example.palomadeapps.ui.theme.PalomadeAppsTheme
import com.example.palomadeapps.views.main.MainViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import androidx.compose.material3.BadgedBox


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun PalomadeApp (
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
                currentRoute != Screen.Camera.route
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
            composable(Screen.Camera2.route){
                CameraScreen2(navigate = navController)
            }

            composable(Screen.Scan.route){
                ScanScreen(navigate = navController)
            }

            composable(Screen.Home.route){
                HomeScreen()
            }

            composable(Screen.Track.route){
                TrackScreen()
            }

            composable(Screen.Camera.route) {
                CameraScreen(navigate = navController)
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
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
                selectedIcon = ImageVector.vectorResource(id = R.drawable.ic_gome),
                unselectedIcon = ImageVector.vectorResource(id = R.drawable.ic_gome),
                hasNews = true,
                screen = Screen.Home,
            ),
            NavigationItem(
                title = stringResource(R.string.menu_cam),
                selectedIcon = ImageVector.vectorResource(id = R.drawable.ic_cameraa),
                unselectedIcon = ImageVector.vectorResource(id = R.drawable.ic_cameraa),
                hasNews = true,
                screen = Screen.Scan
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
                selectedIcon = ImageVector.vectorResource(id = R.drawable.ic_account),
                unselectedIcon = ImageVector.vectorResource(id = R.drawable.ic_account),
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
                selected = selectedItemIndex == index,

                alwaysShowLabel = false,
                label = { Text(item.title, color = colorResource(id = R.color.black)) },
//                selected = currentRoute == item.screen.route,
                onClick = {
                    selectedItemIndex = index
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