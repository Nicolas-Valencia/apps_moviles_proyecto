package com.example.appsmoviles.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appsmoviles.ui.config.RouteScreen
import com.example.appsmoviles.ui.screen.CreatePlace
import com.example.appsmoviles.ui.screens.admin.HomeAdmin
import com.example.appsmoviles.ui.screens.user.HomeUser


@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RouteScreen.Login
    ) {
        composable<RouteScreen.Login> {
            LoginScreen(
                onNavigateToHomeAdmin = {
                    navController.navigate(RouteScreen.HomeAdmin)
                },
                onNavigateToHomeUser = {
                    navController.navigate(RouteScreen.HomeUser)
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

        composable<RouteScreen.HomeAdmin> {
            HomeAdmin()
        }

        composable<RouteScreen.HomeUser> {
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

