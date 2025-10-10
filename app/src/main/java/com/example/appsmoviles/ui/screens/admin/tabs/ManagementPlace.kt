package com.example.appsmoviles.ui.screens.admin.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appsmoviles.R

data class Place(
    val nombreUsuario: String,
    val nombreLugar: String,
    val descripcion: String,
    val direccion: String
)

@Composable
fun ManagementPlace() {
    // Estado del filtro activo
    var selectedTab by remember { mutableStateOf(0) } // 0: Pendientes, 1: Aprobados, 2: Rechazados
    val tabTitles = listOf(
        stringResource(R.string.tab_pendientes),
        stringResource(R.string.tab_aprobados),
        stringResource(R.string.tab_rechazados)
    )

    // Listas simuladas de lugares (datos de ejemplo, no estáticos de interfaz)
    val pendientes = remember {
        mutableStateListOf(
            Place("Carlos Gómez", "Parque Central", "Espacio verde con zonas para picnic", "Calle 10 #15-20"),
            Place("Laura Pérez", "Café Aroma", "Café con ambiente acogedor", "Carrera 8 #12-33")
        )
    }
    val aprobados = remember {
        mutableStateListOf(
            Place("Andrés Ruiz", "Museo de Arte", "Galería con exposiciones permanentes", "Av. Santander #45-50"),
            Place("Sofía Ramírez", "Restaurante El Sabor", "Comida típica de la región", "Calle 5 #22-18"),
            Place("Valentina López", "Mirador del Valle", "Vista panorámica de la ciudad", "Km 3 vía Armenia")
        )
    }
    val rechazados = remember {
        mutableStateListOf(
            Place("Juan Martínez", "Bar Nocturno", "No cumplía normas de registro", "Carrera 10 #14-07")
        )
    }

    val lugares: List<Place> = when (selectedTab) {
        0 -> pendientes
        1 -> aprobados
        2 -> rechazados
        else -> emptyList()
    }

    Scaffold(

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF2F2F2))
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.management_title),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.management_subtitle),
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilterButton(
                    text = tabTitles[0],
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    selectedColor = Color(0xFFC8E6C9)
                )
                FilterButton(
                    text = tabTitles[1],
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    selectedColor = Color(0xFFB3E5FC)
                )
                FilterButton(
                    text = tabTitles[2],
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    selectedColor = Color(0xFFFFCDD2)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (lugares.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = stringResource(R.string.no_items), color = Color.Gray)
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(lugares) { lugar ->
                        PlaceCard(
                            lugar = lugar,
                            estadoIndex = selectedTab,
                            onApprove = {
                                // ejemplo: mover a aprobados si viene de pendientes
                                if (selectedTab == 0) {
                                    pendientes.remove(lugar)
                                    aprobados.add(lugar)
                                }
                            },
                            onReject = {
                                if (selectedTab == 0) {
                                    pendientes.remove(lugar)
                                    rechazados.add(lugar)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FilterButton(text: String, selected: Boolean, onClick: () -> Unit, selectedColor: Color) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) selectedColor else Color.White,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(50),
        modifier = Modifier.height(36.dp)
    ) {
        Text(text = text, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
    }
}

@Composable
fun PlaceCard(
    lugar: Place,
    estadoIndex: Int, // 0=Pendientes, 1=Aprobados, 2=Rechazados
    onApprove: () -> Unit,
    onReject: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        tonalElevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(R.string.user_prefix, lugar.nombreUsuario),
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = stringResource(R.string.place_name_prefix, lugar.nombreLugar), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "${stringResource(R.string.label_description)} ${lugar.descripcion}")
            Text(text = "${stringResource(R.string.label_address)} ${lugar.direccion}")

            if (estadoIndex == 0) {
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onApprove,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81C784))
                    ) {
                        Text(text = stringResource(R.string.approve))
                    }
                    Button(
                        onClick = onReject,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE57373))
                    ) {
                        Text(text = stringResource(R.string.reject))
                    }
                }
            }
        }
    }
}
