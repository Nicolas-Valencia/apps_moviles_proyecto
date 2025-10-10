package com.example.appsmoviles.ui.screens.user.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.appsmoviles.viewmodel.PlacesViewModel
import com.example.appsmoviles.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceDetail(
    placesViewModel: PlacesViewModel,
    padding: PaddingValues,
    id: String
) {
    val place = placesViewModel.findById(id)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Acción del botón */ },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add_place))
            }
        }
    ) { innerPadding ->
        place?.let {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                // ---- Grid de imágenes (principal + secundarias) ----
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    userScrollEnabled = false
                ) {
                    items(it.images.take(4)) { imageUrl ->
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = stringResource(R.string.place_image),
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                // ---- Nombre del restaurante y estado ----
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = stringResource(R.string.open_status),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                // ---- Categoría ----
                Text(
                    text = it.type.name,
                    style = MaterialTheme.typography.bodyMedium
                )

                // ---- Tarjeta de detalles ----
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.details_title),
                            style = MaterialTheme.typography.titleMedium
                        )

                        DetailRow(
                            icon = Icons.Outlined.LocationOn,
                            label = stringResource(R.string.city_label),
                            value = it.address
                        )

                        DetailRow(
                            icon = Icons.Outlined.Home,
                            label = stringResource(R.string.address_label),
                            value = it.address
                        )

                        DetailRow(
                            icon = Icons.Outlined.Phone,
                            label = stringResource(R.string.phone_label),
                            value = it.phones[0]
                        )
                    }
                }
            }
        } ?: run {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(R.string.place_not_found))
            }
        }
    }
}

@Composable
fun DetailRow(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "$label: $value",
            fontSize = 14.sp
        )
    }
}
