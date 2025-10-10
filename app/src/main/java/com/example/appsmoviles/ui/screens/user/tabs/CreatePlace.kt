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
import com.example.appsmoviles.ui.components.DropdownMenu
import com.example.appsmoviles.ui.components.TextFields

@Composable
fun CreatePlace() {
    val context = LocalContext.current

    var nombre by rememberSaveable { mutableStateOf("") }
    var descripcion by rememberSaveable { mutableStateOf("") }
    var horario by rememberSaveable { mutableStateOf("") }
    var categoria by rememberSaveable { mutableStateOf("") }
    var linkUbicacion by rememberSaveable { mutableStateOf("") }
    var telefono by rememberSaveable { mutableStateOf("") }

    // Estados para errores
    var nombreError by remember { mutableStateOf(false) }
    var descripcionError by remember { mutableStateOf(false) }
    var horarioError by remember { mutableStateOf(false) }
    var categoriaError by remember { mutableStateOf(false) }
    var linkUbicacionError by remember { mutableStateOf(false) }
    var telefonoError by remember { mutableStateOf(false) }

    val categorias = listOf(
        stringResource(R.string.txt_restaurant),
        stringResource(R.string.txt_cafeteria),
        stringResource(R.string.txt_bar),
        stringResource(R.string.txt_hotel),
        stringResource(R.string.txt_shop),
        stringResource(R.string.txt_service)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .padding(top = 136.dp) // 120dp for the original top padding + 16dp for the general padding
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

        // Campo Nombre usando componente TextFields
        TextFields(
            value = nombre,
            label = stringResource(R.string.txt_name),
            supportingText = stringResource(R.string.txt_name_error),
            onValueChange = { nombre = it },
            onValidate = { it.isBlank() }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo Descripción usando componente TextFields
        TextFields(
            value = descripcion,
            label = stringResource(R.string.txt_description),
            supportingText = stringResource(R.string.txt_description_error),
            onValueChange = { descripcion = it },
            onValidate = { it.isBlank() }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo Horario usando componente TextFields
        TextFields(
            value = horario,
            label = stringResource(R.string.txt_horario),
            supportingText = stringResource(R.string.txt_horario_error),
            onValueChange = { horario = it },
            onValidate = { it.isBlank() }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Componente DropdownMenu
        DropdownMenu(
            label = stringResource(R.string.txt_select_category),
            list = categorias,
            selectedItem = categoria,
            onValueChange = {
                categoria = it
                categoriaError = false
            },
            isError = categoriaError
        )

        if (categoriaError) {
            Text(
                text = stringResource(R.string.txt_select_category_error),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp, bottom = 16.dp)
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

        // Campo Link Ubicación usando componente TextFields
        TextFields(
            value = linkUbicacion,
            label = stringResource(R.string.txt_link_ubicacion),
            supportingText = stringResource(R.string.txt_link_ubicacion_error),
            onValueChange = { linkUbicacion = it },
            onValidate = { it.isBlank() },
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo Teléfono usando componente TextFields
        TextFields(
            value = telefono,
            label = stringResource(R.string.txt_telefono),
            supportingText = stringResource(R.string.txt_telefono_error),
            onValueChange = { telefono = it },
            onValidate = { it.isBlank() }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                nombreError = nombre.isBlank()
                descripcionError = descripcion.isBlank()
                horarioError = horario.isBlank()
                categoriaError = categoria.isBlank()
                linkUbicacionError = linkUbicacion.isBlank()
                telefonoError = telefono.isBlank()

                if (!nombreError && !descripcionError && !horarioError &&
                    !categoriaError && !linkUbicacionError && !telefonoError) {

                    Toast.makeText(context, context.getString(R.string.txt_place_created), Toast.LENGTH_SHORT).show()
                    Log.d("RegisterScreen", "valores: $nombre, $descripcion, $horario, $categoria, $linkUbicacion, $telefono")

                    nombre = ""
                    descripcion = ""
                    horario = ""
                    categoria = ""
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