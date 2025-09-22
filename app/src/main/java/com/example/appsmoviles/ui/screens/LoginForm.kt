package com.example.appsmoviles.ui.screens

import android.widget.Toast
import com.example.appsmoviles.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource

@Composable
fun LoginForm () {

    var email by rememberSaveable {mutableStateOf("")}
    var password by rememberSaveable {mutableStateOf("")}
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        modifier = Modifier
            .fillMaxSize(),
        content = {

            Text(
                text = "INICIAR SESIÓN"
            )

            OutlinedTextField(
                label = {
                        Text(text = stringResource(R.string.txt_email))
                },
                value = email,
                onValueChange = {
                    email = it
                }
            )
            OutlinedTextField(
                label = {
                    Text(text = stringResource(R.string.txt_password))
                },
                value = password,
                onValueChange = {
                    password = it
                }
            )


            Button(
                onClick = {
                    if (email == "nicolas@gmail.com" && password == "123") {
                        Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Datos incorrectos", Toast.LENGTH_SHORT).show()
                    }
                },
                content = {
                    Text(
                        text = stringResource(R.string.btn_login)
                    )
                }
            )
        }
    )
}