package com.example.appsmoviles.model

import java.time.LocalDateTime

data class Review(

    val id: String,
    val userID: String,
    val placeId: String,
    val rating: Int,
    val comment: String,
    val date: LocalDateTime

)