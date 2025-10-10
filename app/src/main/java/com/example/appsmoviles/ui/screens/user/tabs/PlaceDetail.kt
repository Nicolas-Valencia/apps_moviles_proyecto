package com.example.appsmoviles.ui.screens.user.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.appsmoviles.viewmodel.PlacesViewModel

@Composable
fun PlaceDetail(
    placesViewModel: PlacesViewModel,
    padding: PaddingValues,
    id: String,
){

    val place = placesViewModel.findById(id)


    Box(
        modifier = Modifier
            .padding(padding)
    ){
        Text(
            text = place!!.name
        )
    }

}