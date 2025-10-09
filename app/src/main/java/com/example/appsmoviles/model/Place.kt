package com.example.appsmoviles.model

data class Place(

    val id: String,
    val name: String,
    val description: String,
    val address: String,
    val location: Location,
    val images: List<String>,
    val phones: List<String>,
    val schedule: List<Schedule>,
    val type: PlaceType
)