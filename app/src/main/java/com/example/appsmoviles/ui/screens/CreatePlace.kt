package com.example.pruebacrearlugar.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.example.appsmoviles.R
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast
import androidx.compose.ui.res.stringResource

@Composable
fun CreatePlace() {
    val context = LocalContext.current

    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var horario by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("Selecciona categoría") }
    var linkUbicacion by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }

    // Estados para errores
    var nombreError by remember { mutableStateOf(false) }
    var descripcionError by remember { mutableStateOf(false) }
    var horarioError by remember { mutableStateOf(false) }
    var categoriaError by remember { mutableStateOf(false) }
    var linkUbicacionError by remember { mutableStateOf(false) }
    var telefonoError by remember { mutableStateOf(false) }

    var expandedCategoria by remember { mutableStateOf(false) }
    val categorias = listOf(stringResource(R.string.txt_restaurant), stringResource(R.string.txt_cafeteria), stringResource(R.string.txt_bar), stringResource(R.string.txt_hotel), stringResource(R.string.txt_shop), stringResource(R.string.txt_service))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = stringResource(R.string.txt_create_place),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        )

        Text(
            text = stringResource(R.string.txt_basic_info),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = {
                nombre = it
                nombreError = false
            },
            label = { Text(stringResource(R.string.txt_name)) },
            isError = nombreError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        if (nombreError) {
            Text(
                text = stringResource(R.string.txt_name_error),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
        }

        OutlinedTextField(
            value = descripcion,
            onValueChange = {
                descripcion = it
                descripcionError = false
            },
            label = { Text(stringResource(R.string.txt_description)) },
            isError = descripcionError,
            minLines = 3,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        if (descripcionError) {
            Text(
                text = stringResource(R.string.txt_description_error),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
        }

        OutlinedTextField(
            value = horario,
            onValueChange = {
                horario = it
                horarioError = false
            },
            label = { Text(stringResource(R.string.txt_horario)) },
            isError = horarioError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        if (horarioError) {
            Text(
                text = stringResource(R.string.txt_horario_error),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
        }

        OutlinedTextField(
            value = categoria,
            onValueChange = { },
            readOnly = true,
            label = { Text(stringResource(R.string.txt_select_category)) },
            isError = categoriaError,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expandedCategoria = !expandedCategoria }
                .padding(bottom = 8.dp)
        )

        if (expandedCategoria) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column {
                    categorias.forEach { option ->
                        Text(
                            text = option,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    categoria = option
                                    expandedCategoria = false
                                    categoriaError = false
                                }
                                .padding(16.dp)
                        )
                        if (option != categorias.last()) {
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
        if (categoriaError) {
            Text(
                text = stringResource(R.string.txt_select_category),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
            )
        } else {
            Spacer(modifier = Modifier.height(16.dp))
        }

        Text(
            text = stringResource(R.string.txt_contact_info),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = linkUbicacion,
            onValueChange = {
                linkUbicacion = it
                linkUbicacionError = false
            },
            label = { Text(stringResource(R.string.txt_link_ubicacion)) },
            isError = linkUbicacionError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        if (linkUbicacionError) {
            Text(
                text = stringResource(R.string.txt_link_ubicacion_error),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
        }

        OutlinedTextField(
            value = telefono,
            onValueChange = {
                telefono = it
                telefonoError = false
            },
            label = { Text(stringResource(R.string.txt_telefono)) },
            isError = telefonoError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        if (telefonoError) {
            Text(
                text = stringResource(R.string.txt_telefono_error),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, bottom = 32.dp)
            )
        } else {
            Spacer(modifier = Modifier.height(24.dp))
        }

        Button(
            onClick = {

                nombreError = nombre.isBlank()
                descripcionError = descripcion.isBlank()
                horarioError = horario.isBlank()
                categoriaError = categoria == "Selecciona categoría"
                linkUbicacionError = linkUbicacion.isBlank()
                telefonoError = telefono.isBlank()

                if (!nombreError && !descripcionError && !horarioError &&
                    !categoriaError && !linkUbicacionError && !telefonoError) {

                    Toast.makeText(context, "Lugar guardado exitosamente", Toast.LENGTH_SHORT).show()

                    nombre = ""
                    descripcion = ""
                    horario = ""
                    categoria = "Selecciona categoría"
                    linkUbicacion = ""
                    telefono = ""
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(stringResource(R.string.txt_save), fontSize = 16.sp)
        }
    }
}