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