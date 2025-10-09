package com.example.appsmoviles.ui.screens.user.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.appsmoviles.R
import com.example.appsmoviles.ui.screens.user.nav.RouteTab

@Composable
fun BottomBarUser(

    navController: NavHostController

) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination



    NavigationBar{

        Destination.entries.forEachIndexed { index, destination ->

            val isSelected = currentDestination?.route == destination.route::class.qualifiedName



            //De lugares
            NavigationBarItem(
                label = {
                    Text(
                        text = stringResource(destination.label)

                    )
                },
                onClick = {
                    navController.navigate(destination.route)

                },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = stringResource(destination.label)
                    )
                },
                selected = isSelected

            )
        }

    }
}
enum class Destination(
    val route: RouteTab,
    val label: Int,
    val icon: ImageVector
){
    HOME(RouteTab.Map, R.string.menu_home, Icons.Default.Home),
    SEARCH(RouteTab.Search, R.string.menu_search, Icons.Default.Search),
    PLACES(RouteTab.Places, R.string.menu_places, Icons.Default.Place),
    PROFILE(RouteTab.Profile, R.string.menu_profile, Icons.Default.AccountCircle)
}