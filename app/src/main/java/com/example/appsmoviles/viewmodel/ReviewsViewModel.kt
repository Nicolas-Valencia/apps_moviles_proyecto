package com.example.appsmoviles.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.appsmoviles.model.Review
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
class ReviewsViewModel {

    private val _reviews = MutableStateFlow(emptyList<Review>())
    val reviews: StateFlow<List<Review>> = _reviews.asStateFlow()

    init {
        LoadReviews()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun LoadReviews() {
        _reviews.value = listOf(
            Review(
                id = "1",
                userID = "1",
                placeId = "1",
                rating = 5,
                comment = "Excelente servicio",
                date = LocalDateTime.now()
            ),
            Review(
                id = "2",
                userID = "2",
                placeId = "2",
                rating = 4,
                comment = "Buen lugar para pasar el rato",
                date = LocalDateTime.now()
            ),
            Review(
                id = "3",
                userID = "3",
                placeId = "3",
                rating = 3,
                comment = "Regular",
                date = LocalDateTime.now()
            ),
            Review(
                id = "4",
                userID = "4",
                placeId = "4",
                rating = 2,
                comment = "Mala atención",
                date = LocalDateTime.now()
            ),
            Review(
                id = "5",
                userID = "5",
                placeId = "5",
                rating = 1,
                comment = "Pésimo servicio",
                date = LocalDateTime.now()
            )
        )
    }

}