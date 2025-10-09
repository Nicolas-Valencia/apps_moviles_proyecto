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
            )
        )
    }

}