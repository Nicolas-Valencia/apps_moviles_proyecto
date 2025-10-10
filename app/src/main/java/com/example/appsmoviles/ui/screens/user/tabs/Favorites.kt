package com.example.appsmoviles.ui.screens.user.tabs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.appsmoviles.viewmodel.PlacesViewModel

@Composable
fun Favorites(
    padding: PaddingValues,
    placesViewModel: PlacesViewModel
){

    val places by placesViewModel.places.collectAsState()

    LazyColumn(
        modifier = Modifier
            .padding(padding)
    ) {
        items(places){

            ListItem(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
                    .clickable{

                    },
                headlineContent = { Text(text = it.name) },
                supportingContent = { Text(text = it.description) },
                trailingContent = {
                    AsyncImage(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .width(100.dp)
                            .height(100.dp),
                        model = it.images[0],
                        contentDescription = it.name,
                        contentScale = ContentScale.Crop
                    )
                }
            )
        }
    }

}