import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
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
){

    val navBarStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBarStackEntry?.destination

    NavigationBar (

    ) {

        Destination.entries.forEachIndexed {index, destination ->

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
    val icon: ImageVector,
) {
    HOME(RouteTab.Home, R.string.menu_home, Icons.Outlined.Home),
    SEARCH(RouteTab.Search, R.string.menu_search, Icons.Outlined.Search),
    ADD(RouteTab.CreatePlace, R.string.menu_create_place, Icons.Outlined.Add),
    FAVORITES(RouteTab.Favorites, R.string.menu_favorites, Icons.Outlined.FavoriteBorder),
    PROFILE(RouteTab.Profile, R.string.menu_profile, Icons.Outlined.Person)
}