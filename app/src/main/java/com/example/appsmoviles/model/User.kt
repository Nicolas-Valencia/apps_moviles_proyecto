package com.example.appsmoviles.model

data class User(

    var id: String = "",
    val name: String= "",
    val username: String= "",
    val role: Role = Role.USER,
    val city: String= "Armenia",
    val email: String= "",
    val password: String= ""

)