package com.example.appsmoviles.ui.screens

import android.util.Patterns
import android.widget.Toast
import com.example.appsmoviles.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import com.example.appsmoviles.ui.components.TextFields
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import com.example.appsmoviles.model.Role
import com.example.appsmoviles.viewmodel.UsersViewModel

@Composable
fun LoginScreen (

    usersViewModel: UsersViewModel,
    onNavigateToHomeUser: () -> Unit,
    onNavigateToHomeAdmin: () -> Unit,
    onNavigateToRegister: () -> Unit
) {

    var email by rememberSaveable {mutableStateOf("")}
    var password by rememberSaveable {mutableStateOf("")}

    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        content = {

            Text(
                text = stringResource(R.string.btn_login),
            )

            TextFields(
                value = email,
                label = stringResource(R.string.txt_email),
                supportingText = stringResource(R.string.txt_email_error),
                icon = Icons.Outlined.Email,
                onValueChange = {
                    email = it
                },
                onValidate = {
                    email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
                }
            )

            TextFields(
                isPassword = true,
                value = password,
                label = stringResource(R.string.txt_password),
                supportingText = stringResource(R.string.txt_password_error),
                icon = Icons.Outlined.Lock,
                onValueChange = {
                    password = it
                },
                onValidate = {
                    password.isBlank() || password.length < 6
                }
            )

            Button(
                onClick = {

                    val userLogged = usersViewModel.login(email, password)

                    if(userLogged != null){

                        if (userLogged.role == Role.ADMIN) {
                            onNavigateToHomeAdmin()
                            Toast.makeText(context, "Inicio de sesión exitoso, bienvenido ${userLogged.name}", Toast.LENGTH_SHORT).show()
                        } else {
                            onNavigateToHomeUser()
                            Toast.makeText(context, "Inicio de sesión exitoso, bienvenido ${userLogged.name}", Toast.LENGTH_SHORT).show()
                        }
                    } else{
                        Toast.makeText(context, "Datos incorrectos", Toast.LENGTH_SHORT).show()
                    }
                },
                content = {
                    Icon(
                        imageVector = Icons.Outlined.AccountCircle,
                        contentDescription = stringResource(R.string.btn_login)
                    )
                    Text(
                        text = stringResource(R.string.btn_login)
                    )
                }
            )

            Button(
                onClick = {
                    onNavigateToRegister()
                },
                content = {
                    Icon(
                        imageVector = Icons.Outlined.AddCircle,
                        contentDescription = stringResource(R.string.btn_register)
                    )
                    Text(
                        text = stringResource(R.string.txt_create_account)
                    )
                }
            )
        }
    )
}