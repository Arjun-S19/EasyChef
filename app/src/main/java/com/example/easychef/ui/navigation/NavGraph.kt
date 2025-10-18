package com.example.easychef.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.easychef.ui.features.LoginScreen.LoginScreen
import com.example.easychef.ui.features.home.HomeScreen
import com.example.easychef.ui.navigation.HomeScreen as HomeScreenRoute
import com.example.easychef.ui.navigation.LoginScreen as LoginScreenRoute

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
    ) {
    NavHost(navController, startDestination = LoginScreenRoute ) {
        composable<LoginScreenRoute> {
            LoginScreen(
                onGoToHome = {id -> navController.navigate(HomeScreenRoute(id))}
            )
        }
        composable<HomeScreenRoute> {
            val args = it.toRoute<HomeScreenRoute>()
            HomeScreen(
                id = args.id
            )
        }
    }
}
