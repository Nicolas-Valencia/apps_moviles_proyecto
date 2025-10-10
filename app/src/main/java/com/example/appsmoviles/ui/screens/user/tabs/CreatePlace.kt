package com.example.appsmoviles.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.example.appsmoviles.R
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import com.example.appsmoviles.model.Location
import com.example.appsmoviles.model.Place
import com.example.appsmoviles.model.PlaceType
import com.example.appsmoviles.model.Schedule
import com.example.appsmoviles.ui.components.DropdownMenu
import com.example.appsmoviles.ui.components.TextFields
import java.time.LocalTime
import java.util.UUID

@Composable
fun CreatePlace(padding: PaddingValues = PaddingValues(0.dp)) {
    val context = LocalContext.current

    var nombre by rememberSaveable { mutableStateOf("") }
    var descripcion by rememberSaveable { mutableStateOf("") }
    var direccion by rememberSaveable { mutableStateOf("") }
    var latitud by rememberSaveable { mutableStateOf("") }
    var longitud by rememberSaveable { mutableStateOf("") }
    var imagenesUrls by rememberSaveable { mutableStateOf("") }
    var telefonos by rememberSaveable { mutableStateOf("") }
    var tipoSeleccionado by rememberSaveable { mutableStateOf("") }

    var nombreError by remember { mutableStateOf(false) }
    var descripcionError by remember { mutableStateOf(false) }
    var direccionError by remember { mutableStateOf(false) }
    var tipoError by remember { mutableStateOf(false) }

    val tiposLugar = listOf(
        "Restaurante",
        "Bar",
        "Hotel",
        "Parque",
        "Tienda",
        "Otros"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.txt_create_place),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )

        Text(
            text = stringResource(R.string.txt_basic_info),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        TextFields(
            value = nombre,
            label = stringResource(R.string.txt_name),
            supportingText = stringResource(R.string.txt_name_error),
            onValueChange = { nombre = it },
            onValidate = { it.isBlank() }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextFields(
            value = descripcion,
            label = stringResource(R.string.txt_description),
            supportingText = stringResource(R.string.txt_description_error),
            onValueChange = { descripcion = it },
            onValidate = { it.isBlank() }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextFields(
            value = direccion,
            label = "Dirección",
            supportingText = "La dirección es requerida",
            onValueChange = { direccion = it },
            onValidate = { it.isBlank() }
        )
        Spacer(modifier = Modifier.height(8.dp))

        DropdownMenu(
            label = "Tipo de lugar",
            list = tiposLugar,
            selectedItem = tipoSeleccionado,
            onValueChange = {
                tipoSeleccionado = it
                tipoError = false
            },
            isError = tipoError
        )
        if (tipoError) {
            Text(
                text = "Selecciona un tipo de lugar",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp, bottom = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Ubicación",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = latitud,
            onValueChange = { latitud = it },
            label = { Text("Latitud") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = longitud,
            onValueChange = { longitud = it },
            label = { Text("Longitud") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.txt_contact_info),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = telefonos,
            onValueChange = { telefonos = it },
            label = { Text("Teléfonos") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = imagenesUrls,
            onValueChange = { imagenesUrls = it },
            label = { Text("URLs de imágenes") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                nombreError = nombre.isBlank()
                descripcionError = descripcion.isBlank()
                direccionError = direccion.isBlank()
                tipoError = tipoSeleccionado.isBlank()

                if (!nombreError && !descripcionError && !direccionError && !tipoError) {
                    val placeType = when(tipoSeleccionado) {
                        "Restaurante" -> PlaceType.RESTAURANT
                        "Bar" -> PlaceType.BAR
                        "Hotel" -> PlaceType.HOLTEL
                        "Parque" -> PlaceType.PARK
                        "Tienda" -> PlaceType.SHOPPING
                        else -> PlaceType.OTHER
                    }

                    val lat = latitud.toDoubleOrNull() ?: 0.0
                    val lon = longitud.toDoubleOrNull() ?: 0.0

                    val place = Place(
                        id = UUID.randomUUID().toString(),
                        name = nombre,
                        description = descripcion,
                        address = direccion,
                        location = Location(latitude = lat, longitude = lon),
                        images = if (imagenesUrls.isNotBlank())
                            imagenesUrls.split(",").map { it.trim() }
                        else
                            emptyList(),
                        phones = if (telefonos.isNotBlank())
                            telefonos.split(",").map { it.trim() }
                        else
                            emptyList(),
                        schedule = emptyList(),
                        type = placeType
                    )

                    Log.d("CreatePlace", "Lugar creado: $place")
                    Toast.makeText(context, context.getString(R.string.txt_place_created), Toast.LENGTH_SHORT).show()

                    nombre = ""
                    descripcion = ""
                    direccion = ""
                    latitud = ""
                    longitud = ""
                    imagenesUrls = ""
                    telefonos = ""
                    tipoSeleccionado = ""
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(stringResource(R.string.txt_save), fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}