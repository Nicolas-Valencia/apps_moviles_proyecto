package com.example.appsmoviles.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appsmoviles.ui.config.RouteScreen
import com.example.appsmoviles.ui.screen.CreatePlace

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RouteScreen.Login
    ) {
        composable<RouteScreen.Login> {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(RouteScreen.Register)
                },
                onNavigateToCreatePlace = {
                    navController.navigate(RouteScreen.CreatePlace)
                }
            )
        }

        composable<RouteScreen.Register> {
            RegisterScreen(
                onNavigateToLogin = {
                    navController.navigate(RouteScreen.Login)
                }
            )
        }

        composable<RouteScreen.Home> {
            HomeScreen()
        }

        composable<RouteScreen.CreatePlace> {
            CreatePlace()
        }
    }

}