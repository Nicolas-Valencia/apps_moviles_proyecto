package com.example.appsmoviles.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.example.appsmoviles.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAccount(
    onNavigateBack: () -> Unit = {}
) {
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.txt_edit_account),
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.txt_edit_profile_picture),
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
                    Text(stringResource(R.string.txt_edit_profile_picture))
                }
            }

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
                value = username,
                onValueChange = {
                    username = it
                    usernameError = false
                },
                label = { Text(stringResource(R.string.txt_user)) },
                isError = usernameError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            if (usernameError) {
                Text(
                    text = stringResource(R.string.txt_user_error),
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
                label = { Text(stringResource(R.string.txt_city)) },
                isError = ciudadError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            if (ciudadError) {
                Text(
                    text = stringResource(R.string.txt_city_error),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp, bottom = 24.dp)
                )
            } else {
                Spacer(modifier = Modifier.height(16.dp))
            }

            Text(
                text = stringResource(R.string.txt_account_info),
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
                label = { Text(stringResource(R.string.txt_email)) },
                isError = emailError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            if (emailError) {
                Text(
                    text = stringResource(R.string.txt_email_error),
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
                label = { Text(stringResource(R.string.txt_edit_rol)) },
                isError = rolError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            if (rolError) {
                Text(
                    text = stringResource(R.string.txt_rol_error),
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
                Text(stringResource(R.string.txt_save_changes), fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
