package com.example.appsmoviles.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appsmoviles.ui.config.RouteScreen
import com.example.appsmoviles.ui.screen.CreatePlace
import com.example.appsmoviles.ui.screens.user.HomeUser


@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RouteScreen.Home
    ) {
        composable<RouteScreen.Login> {
            LoginScreen(
                onNavigateToHome = {
                    navController.navigate(RouteScreen.Home)
                },
                onNavigateToRegister = {
                    navController.navigate(RouteScreen.Register)
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
            HomeUser()
        }

        composable<RouteScreen.CreatePlace> {
            CreatePlace()
        }

        composable<RouteScreen.EditAccount> {
            EditAccount()
        }
    }

}

