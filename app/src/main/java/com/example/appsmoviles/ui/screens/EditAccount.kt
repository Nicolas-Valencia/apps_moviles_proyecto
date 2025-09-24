package com.example.appsmoviles.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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

@Composable
fun EditAccount() {
    val context = LocalContext.current

    var nombre by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var ciudad by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var rol by remember { mutableStateOf("") }

    var nombreError by remember { mutableStateOf(false) }
    var usernameError by remember { mutableStateOf(false) }
    var ciudadError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var rolError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "Editar Perfil",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Foto de perfil",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = {
                    Toast.makeText(
                        context,
                        "Funcionalidad de foto próximamente",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            ) {
                Text("Cambiar foto de perfil")
            }
        }

        Text(
            text = "Información básica",
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
            label = { Text("Nombre") },
            isError = nombreError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        if (nombreError) {
            Text(
                text = "El nombre es requerido",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
        }

        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                usernameError = false
            },
            label = { Text("Username") },
            isError = usernameError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        if (usernameError) {
            Text(
                text = "El username es requerido",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
        }

        OutlinedTextField(
            value = ciudad,
            onValueChange = {
                ciudad = it
                ciudadError = false
            },
            label = { Text("Ciudad") },
            isError = ciudadError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        if (ciudadError) {
            Text(
                text = "La ciudad es requerida",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, bottom = 24.dp)
            )
        } else {
            Spacer(modifier = Modifier.height(16.dp))
        }

        Text(
            text = "Información de la cuenta",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = false
            },
            label = { Text("Email") },
            isError = emailError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        if (emailError) {
            Text(
                text = "El email es requerido",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
        }

        OutlinedTextField(
            value = rol,
            onValueChange = {
                rol = it
                rolError = false
            },
            label = { Text("Rol") },
            isError = rolError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        if (rolError) {
            Text(
                text = "El rol es requerido",
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
                usernameError = username.isBlank()
                ciudadError = ciudad.isBlank()
                emailError = email.isBlank()
                rolError = rol.isBlank()

                if (!nombreError && !usernameError && !ciudadError &&
                    !emailError && !rolError
                ) {

                    Toast.makeText(context, "Perfil actualizado correctamente", Toast.LENGTH_SHORT)
                        .show()

                    nombre = ""
                    username = ""
                    ciudad = ""
                    email = ""
                    rol = ""
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Guardar Cambios", fontSize = 16.sp)
        }
    }
}
