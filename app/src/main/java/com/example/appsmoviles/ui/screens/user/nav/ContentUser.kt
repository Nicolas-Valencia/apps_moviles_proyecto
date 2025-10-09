import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appsmoviles.ui.screen.CreatePlace
import com.example.appsmoviles.ui.screens.user.nav.RouteTab
import com.example.appsmoviles.ui.screens.user.tabs.Favorites
import com.example.appsmoviles.ui.screens.user.tabs.Home
import com.example.appsmoviles.ui.screens.user.tabs.Profile
import com.example.appsmoviles.ui.screens.user.tabs.Search

@Composable
fun ContentUser(
    padding: PaddingValues,
    navController: NavHostController
) {

    NavHost(
        modifier = Modifier.padding(padding),
        navController = navController,
        startDestination = RouteTab.Home
    ){
        composable<RouteTab.Home> {
            Home()
        }
        composable<RouteTab.Search> {
            Search()
        }
        composable<RouteTab.CreatePlace> {
            CreatePlace()
        }
        composable<RouteTab.Favorites> {
            Favorites()
        }
        composable<RouteTab.Profile> {
            Profile()
        }
    }

}