package com.example.appsmoviles.viewmodel

import com.example.appsmoviles.model.Location
import com.example.appsmoviles.model.Place
import com.example.appsmoviles.model.PlaceType
import com.example.appsmoviles.model.Schedule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalTime

class PlacesViewModel {

    private val _places = MutableStateFlow(emptyList<Place>())
    val places: StateFlow<List<Place>> = _places.asStateFlow()
    init {
        LoadPlaces()
    }

    fun LoadPlaces() {
        _places.value = listOf(
            Place(
                id = "1",
                name = "Restaurante 1",
                description = "Descripción del restaurante 1",
                address = "Dirección del restaurante 1",
                location = Location(1.0, 2.0),
                images = listOf("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRbv-Dn8ZP4OmnECVuhL_w9VAa7YuJoAkZXSQ&s"),
                phones = listOf("123456789", "987654321"),
                schedule = listOf(),
                type = PlaceType.RESTAURANT
            ),
            Place(
                id = "2",
                name = "Tienda",
                description = "Descripción de la tienda",
                address = "Dirección de la tienda",
                location = Location(3.0, 4.0),
                images = listOf("https://www.shutterstock.com/image-photo/south-sulawesi-indonesia-september-10-260nw-2577424109.jpg"),
                phones = listOf("987654321", "123456789"),
                schedule = listOf(),
                type = PlaceType.SHOPPING
            ),
            Place(
                id= "3",
                name = "Parque del Cafe",
                description = "Descripción del parque",
                address = "Dirección del parque",
                location = Location(5.0, 6.0),
                images = listOf("https://caracol.com.co/resizer/v2/6DIRSYCRKNHW5LEBB5HTZOFC6Y.png?auth=f7fb5f73b7d4e5faf131ba13f379c924e19983a66f31cb12af3560535634633e&width=650&height=488&quality=70&smart=true"),
                phones = listOf("987654321", "123456789"),
                schedule = listOf(),
                type = PlaceType.SHOPPING
            ),
            Place(
                id = "4",
                name = "Restaurante 4",
                description = "Descripción del restaurante 4",
                address = "Dirección del restaurante 4",
                location = Location(7.0, 8.0),
                images = listOf("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRbv-Dn8ZP4OmnECVuhL_w9VAa7YuJoAkZXSQ&s"),
                phones = listOf("123456789", "987654321"),
                schedule = listOf(),
                type = PlaceType.RESTAURANT
            ),
            Place(
                id = "5",
                name = "Tienda 5",
                description = "Descripción de la tienda 5",
                address = "Dirección de la tienda 5",
                location = Location(9.0, 10.0),
                images = listOf("https://www.shutterstock.com/image-photo/south-sulawesi-indonesia-september-10-260nw-2577424109.jpg"),
                phones = listOf("987654321", "123456789"),
                schedule = listOf(),
                type = PlaceType.SHOPPING
            ),
            Place(
                id = "6",
                name = "Restaurante 6",
                description = "Descripción del restaurante 6",
                address = "Dirección del restaurante 6",
                location = Location(11.0, 12.0),
                images = listOf("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRbv-Dn8ZP4OmnECVuhL_w9VAa7YuJoAkZXSQ&s"),
                phones = listOf("123456789", "987654321"),
                schedule = listOf(),
                type = PlaceType.RESTAURANT
            ),
            Place(
                id = "7",
                name = "Tienda 7",
                description = "Descripción de la tienda 7",
                address = "Dirección de la tienda 7",
                location = Location(13.0, 14.0),
                images = listOf("https://www.shutterstock.com/image-photo/south-sulawesi-indonesia-september-10-260nw-2577424109.jpg"),
                phones = listOf("987654321", "123456789"),
                schedule = listOf(),
                type = PlaceType.SHOPPING
            ),
            Place(
                id = "8",
                name = "Bar",
                description = "Descripción del bar ",
                address = "Dirección del bar",
                location = Location(15.0, 16.0),
                images = listOf("https://caracol.com.co/resizer/v2/6DIRSYCRKNHW5LEBB5HTZOFC6Y.png?auth=f7fb5f73b7d4e5faf131ba13f379c924e19983a66f31cb12af3560535634633"),
                phones = listOf("987654321", "123456789"),
                schedule = listOf(),
                type = PlaceType.BAR
            ),
            Place(
                id = "9",
                name = "Restaurante 9",
                description = "Descripción del restaurante 9",
                address = "Dirección del restaurante 9",
                location = Location(17.0, 18.0),
                images = listOf("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRbv-Dn8ZP4OmnECVuhL_w9VAa7YuJoAkZXSQ&s"),
                phones = listOf("123456789", "987654321"),
                schedule = listOf(),
                type = PlaceType.RESTAURANT
            ),
            Place(
                id = "10",
                name = "Tienda 10",
                description = "Descripción de la tienda 10",
                address = "Dirección de la tienda 10",
                location = Location(19.0, 20.0),
                images = listOf("https://www.shutterstock.com/image-photo/south-sulawesi-indonesia-september-10-260nw-2577424109.jpg"),
                phones = listOf("987654321", "123456789"),
                schedule = listOf(),
                type = PlaceType.SHOPPING
            ),
            Place(
                id = "11",
                name = "Restaurante 11",
                description = "Descripción del restaurante 11",
                address = "Dirección del restaurante 11",
                location = Location(21.0, 22.0),
                images = listOf("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRbv-Dn8ZP4OmnECVuhL_w9VAa7YuJoAkZXSQ&s"),
                phones = listOf("123456789", "987654321"),
                schedule = listOf(),
                type = PlaceType.RESTAURANT
            ),
            Place(
                id = "12",
                name = "Tienda 12",
                description = "Descripción de la tienda 12",
                address = "Dirección de la tienda 12",
                location = Location(23.0, 24.0),
                images = listOf("https://www.shutterstock.com/image-photo/south-sulawesi-indonesia-september-10-260nw-2577424109.jpg"),
                phones = listOf("987654321", "123456789"),
                schedule = listOf(),
                type = PlaceType.SHOPPING
            ),
            Place(
                id = "13",
                name = "Restaurante 13",
                description = "Descripción del restaurante 13",
                address = "Dirección del restaurante 13",
                location = Location(25.0, 26.0),
                images = listOf("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRbv-Dn8ZP4OmnECVuhL_w9VAa7YuJoAkZXSQ&s"),
                phones = listOf("123456789", "987654321"),
                schedule = listOf(),
                type = PlaceType.RESTAURANT
            )
        )
    }

    fun create(place: Place){
        _places.value = _places.value + place
    }

    fun update(place: Place){
        _places.value = _places.value.map {
            if(it.id == place.id){
                place
            }else{
                it
            }
        }
    }

    fun findById(id: String): Place?{
        return _places.value.find { it.id == id }
    }

    fun findByType(type: PlaceType): List<Place>{
        return _places.value.filter { it.type == type }
    }

    fun findByName(name: String): List<Place>{
        return _places.value.filter { it.name.contains(name) }
    }

    fun findByLocation(location: Location): List<Place>{
        return _places.value.filter { it.location == location }
    }


}