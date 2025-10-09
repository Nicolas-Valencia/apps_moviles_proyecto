package com.example.appsmoviles.ui.screens.admin.nav

import kotlinx.serialization.Serializable

sealed class RouteTab {

    @Serializable
    data object AdminHome : RouteTab()

    @Serializable
    data object ManagementPlace : RouteTab()

    @Serializable
    data object Rules : RouteTab()

    @Serializable
    data object Profile : RouteTab()

}