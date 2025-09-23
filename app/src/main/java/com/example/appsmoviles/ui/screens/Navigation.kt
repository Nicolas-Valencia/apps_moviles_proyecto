package com.example.appsmoviles.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appsmoviles.ui.config.RouteScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RouteScreen.Login
    ) {
        composable<RouteScreen.Login> {
            LoginScreen()
        }

        composable<RouteScreen.Register> {
            RegisterScreen()
        }

        composable<RouteScreen.Home> {
            HomeScreen()
        }
    }

}