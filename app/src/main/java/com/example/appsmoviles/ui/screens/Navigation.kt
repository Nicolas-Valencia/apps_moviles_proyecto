package com.example.appsmoviles.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appsmoviles.model.Role
import com.example.appsmoviles.ui.config.RouteScreen
import com.example.appsmoviles.ui.screen.CreatePlace
import com.example.appsmoviles.ui.screens.admin.HomeAdmin
import com.example.appsmoviles.ui.screens.user.HomeUser
import com.example.appsmoviles.util.SharedPrefUtil
import com.example.appsmoviles.viewmodel.UsersViewModel


@Composable
fun Navigation() {

    val context = LocalContext.current
    val navController = rememberNavController()
    val usersViewModel: UsersViewModel = viewModel()
    val user = SharedPrefUtil.getPreference(context)

    val startDestination = if (user.isEmpty()) {
        RouteScreen.Login
    } else {
        if (user["rol"] == "ADMIN") {
            RouteScreen.HomeAdmin
        } else {
            RouteScreen.HomeUser
            }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<RouteScreen.Login> {
            LoginScreen(
                usersViewModel = usersViewModel,
                onNavigateToHome = { userId, rol ->

                    SharedPrefUtil.savePreference(context, userId, rol)

                    if (rol == Role.ADMIN) {
                        navController.navigate(RouteScreen.HomeAdmin)
                    } else {
                        navController.navigate(RouteScreen.HomeUser)
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(RouteScreen.Register)
                }
            )
        }

        composable<RouteScreen.Register> {
            RegisterScreen(
                usersViewModel = usersViewModel,
                onNavigateToLogin = {
                    navController.navigate(RouteScreen.Login)
                }
            )
        }

        composable<RouteScreen.HomeAdmin> {
            HomeAdmin(
                onNavigateToLogin = {
                    navController.navigate(RouteScreen.Login)
                }
            )
        }

        composable<RouteScreen.HomeUser> {
            HomeUser(
                onNavigateToEditAccount = {
                    navController.navigate(RouteScreen.EditAccount)
                },
                onNavigateToLogin = {
                    navController.navigate(RouteScreen.Login)
                }
            )
        }

        composable<RouteScreen.CreatePlace> {
            CreatePlace()
        }

        composable<RouteScreen.EditAccount> {
            EditAccount(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }

}
