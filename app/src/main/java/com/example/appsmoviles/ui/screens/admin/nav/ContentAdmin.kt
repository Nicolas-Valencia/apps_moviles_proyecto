package com.example.appsmoviles.ui.screens.admin.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appsmoviles.ui.screens.admin.tabs.AdminHome
import com.example.appsmoviles.ui.screens.admin.tabs.ManagementPlace
import com.example.appsmoviles.ui.screens.admin.tabs.Profile
import com.example.appsmoviles.ui.screens.admin.tabs.Rules

@Composable
fun ContentAdmin(
    padding: PaddingValues,
    navController: NavHostController
){

    NavHost(
        modifier = Modifier.padding(padding),
        navController = navController,
        startDestination = RouteTab.AdminHome
    ){
        composable<RouteTab.AdminHome> {
            AdminHome()
        }
        composable<RouteTab.ManagementPlace>{
            ManagementPlace()
        }
        composable<RouteTab.Rules> {
            Rules()
        }
        composable<RouteTab.Profile> {
            Profile()
        }
    }


}