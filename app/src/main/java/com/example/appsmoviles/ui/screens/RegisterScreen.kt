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
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import com.example.appsmoviles.model.Role
import com.example.appsmoviles.model.User
import com.example.appsmoviles.ui.components.TextFields
import com.example.appsmoviles.viewmodel.UsersViewModel
import java.util.UUID

@Composable
fun RegisterScreen (

    usersViewModel: UsersViewModel,
    onNavigateToLogin: () -> Unit
) {

    var name by rememberSaveable { mutableStateOf("") }
    var user by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var isEmailError by rememberSaveable { mutableStateOf(false) }

    var city by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        content = {

            Text(
                text = stringResource(R.string.btn_register),
            )

            TextFields(
                value = name,
                label = stringResource(R.string.txt_name),
                supportingText = stringResource(R.string.txt_name_error),
                icon = Icons.Outlined.Person,
                onValueChange = {
                    name = it
                },
                onValidate = {
                    name.isBlank()
                }
            )
            TextFields(
                value = user,
                label = stringResource(R.string.txt_user),
                supportingText = stringResource(R.string.txt_user_error),
                icon = Icons.Outlined.AccountCircle,
                onValueChange = {
                    user = it
                },
                onValidate = {
                    user.isBlank()
                }
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
                value = city,
                label = stringResource(R.string.txt_city),
                supportingText = stringResource(R.string.txt_city_error),
                icon = Icons.Outlined.LocationOn,
                onValueChange = {
                    city = it
                },
                onValidate = {
                    city.isBlank()
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

                    val user = User(
                        id = UUID.randomUUID().toString(),
                        name = name,
                        username = user,
                        city = city,
                        role = Role.USER,
                        email = email,
                        password = password
                    )

                    usersViewModel.create(user)

                    if (name.isNotBlank()  && email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches() && city.isNotBlank() && password.isNotBlank() && password.length >= 6) {
                        Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                        onNavigateToLogin()
                    } else {
                        Toast.makeText(context, "Llene correctamente los campos", Toast.LENGTH_SHORT).show()
                    }
                },
                content = {
                    Icon(
                        imageVector = Icons.Outlined.AddCircle,
                        contentDescription = stringResource(R.string.btn_register)
                    )
                    Text(
                        text = stringResource(R.string.btn_register)
                    )
                }
            )

            Button(
                onClick = {
                    onNavigateToLogin()
                },
                content = {
                    Icon(
                        imageVector = Icons.Outlined.AccountCircle,
                        contentDescription = stringResource(R.string.btn_register)
                    )
                    Text(
                        text = stringResource(R.string.txt_login)
                    )
                }
            )
        }
    )
}