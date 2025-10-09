package com.example.appsmoviles.ui.config

import kotlinx.serialization.Serializable

sealed class RouteScreen {

    @Serializable
    data object HomeUser : RouteScreen()

    @Serializable
    data object HomeAdmin : RouteScreen()

    @Serializable
    data object Login : RouteScreen()

    @Serializable
    data object Register : RouteScreen()

    @Serializable
    data object CreatePlace : RouteScreen()

    @Serializable
    data object EditAccount : RouteScreen()

}