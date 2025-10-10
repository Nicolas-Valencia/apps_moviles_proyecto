package com.example.appsmoviles.ui.screens.user.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.appsmoviles.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(padding: PaddingValues) {
    var query by remember { mutableStateOf("") }
    val places = listOf(
        Triple("Panadería La Espiga", "Deliciosos panes artesanales", "Abierto"),
        Triple("Café Andino", "Café orgánico y postres", "Abierto"),
        Triple("Bar Nocturno", "Cócteles y música en vivo", "Cerrado")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp)
    ) {
        SearchBar(
            query = query,
            onQueryChange = { query = it },
            onSearch = {},
            active = false,
            onActiveChange = {},
            placeholder = { Text(stringResource(R.string.txt_search_placeholder)) },
            trailingIcon = {
                IconButton(onClick = { /* abrir filtros */ }) {
                    Icon(Icons.Outlined.Search, contentDescription = "Filter")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {}

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(0xFFD6E9FF), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(stringResource(R.string.txt_map_preview))
        }

        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { /* Ver mapa completo */ }) {
            Text(stringResource(R.string.txt_view_full_map))
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.txt_found_places),
            fontWeight = FontWeight.Bold
        )

        LazyColumn {
            items(places) { (name, description, status) ->
                PlaceCard(name, description, status)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
