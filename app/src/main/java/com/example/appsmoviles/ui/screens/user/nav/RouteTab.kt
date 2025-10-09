package com.example.appsmoviles.ui.screens.user.nav

import kotlinx.serialization.Serializable

sealed class RouteTab {

    @Serializable
    data object Home : RouteTab()

    @Serializable
    data object Search : RouteTab()

    @Serializable
    data object CreatePlace : RouteTab()

    @Serializable
    data object Favorites : RouteTab()

    @Serializable
    data object Profile : RouteTab()

}