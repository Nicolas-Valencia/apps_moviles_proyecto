import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.appsmoviles.ui.screen.CreatePlace
import com.example.appsmoviles.ui.screens.user.nav.RouteTab
import com.example.appsmoviles.ui.screens.user.tabs.Favorites
import com.example.appsmoviles.ui.screens.user.tabs.Home
import com.example.appsmoviles.ui.screens.user.tabs.PlaceDetail
import com.example.appsmoviles.ui.screens.user.tabs.Profile
import com.example.appsmoviles.ui.screens.user.tabs.Search
import com.example.appsmoviles.viewmodel.PlacesViewModel

@Composable
fun ContentUser(
    navController: NavHostController,
    padding: PaddingValues,
    onNavigateToEditAccount: () -> Unit
) {

    val placesViewModel: PlacesViewModel = PlacesViewModel()

    NavHost(
        navController = navController,
        startDestination = RouteTab.Home
    ){

        composable<RouteTab.Home> {
            Home()
        }
        composable<RouteTab.Search> {
            Search(
                padding = padding
            )
        }
        composable<RouteTab.CreatePlace> {
            CreatePlace(padding = padding)
        }
        composable<RouteTab.Favorites> {
            Favorites(
                padding = padding,
                placesViewModel = placesViewModel,
                onNavigateToPlaceDetail = {
                    navController.navigate(RouteTab.PlaceDetail(it))
                }
            )
        }
        composable<RouteTab.Profile> {
            Profile(
                padding = padding,
                onNavigateToEditAccount = onNavigateToEditAccount
            )
        }
        composable<RouteTab.PlaceDetail> {
            val args = it.toRoute<RouteTab.PlaceDetail>()
            PlaceDetail(
                placesViewModel = placesViewModel,
                padding = padding,
                id = args.id
            )
        }
    }

}