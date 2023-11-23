package com.cafealyzer.cafealyzer

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cafealyzer.cafealyzer.ui.component.homepage.BottomBar
import com.cafealyzer.cafealyzer.ui.navigation.Screen
import com.cafealyzer.cafealyzer.ui.screen.HistoryScreen
import com.cafealyzer.cafealyzer.ui.screen.HomeScreen
import com.cafealyzer.cafealyzer.ui.screen.MapsScreen
import com.cafealyzer.cafealyzer.ui.screen.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isProfileScreen = currentRoute == Screen.Profile.route
    val isDetailScreen = currentRoute == Screen.History.route
    val isHomeScreen = currentRoute == Screen.Home.route
    Scaffold(
        bottomBar = {
            if (isProfileScreen || isDetailScreen || isHomeScreen) {
                BottomBar(navController)
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding),
            enterTransition = {
                // you can change whatever you want transition
                EnterTransition.None
            },
            exitTransition = {
                // you can change whatever you want transition
                ExitTransition.None
            }
        ) {
            composable(Screen.Home.route) {
                HomeScreen(onCariClick = {
                    navController.navigate(Screen.Maps.route)
                })
            }
            composable(Screen.History.route) {
                HistoryScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(Screen.Maps.route) {
                MapsScreen()
            }
        }
    }
}