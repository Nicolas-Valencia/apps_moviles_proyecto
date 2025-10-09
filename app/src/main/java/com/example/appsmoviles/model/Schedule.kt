package com.example.appsmoviles.model

import java.time.LocalTime

data class Schedule(

    val day: String,
    val open: LocalTime,
    val close: LocalTime

)