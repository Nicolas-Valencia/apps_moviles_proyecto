package com.example.appsmoviles.ui.screens.admin.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.appsmoviles.R
import com.example.appsmoviles.ui.screens.admin.nav.RouteTab

@Composable
fun BottomBarAdmin(
    navController: NavHostController
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar{

        Destination.entries.forEachIndexed { index, destination ->

            val isSelected = currentDestination?.route == destination.route::class.qualifiedName

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
    HOME(RouteTab.Home, R.string.menu_homeAdmin, Icons.Outlined.Home),
    MANAGEMENTPLACE(RouteTab.ManagementPlace, R.string.menu_managementPlace, Icons.Outlined.Edit),
    RULES(RouteTab.Rules, R.string.menu_rules, Icons.Outlined.Info),
    PROFILE(RouteTab.Profile, R.string.menu_profile, Icons.Outlined.Person)
}