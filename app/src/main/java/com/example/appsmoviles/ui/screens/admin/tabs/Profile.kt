package com.example.appsmoviles.ui.screens.admin.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appsmoviles.R

@Composable
fun Profile() {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // 👈 Habilita el scroll vertical
                .background(Color(0xFFF2F2F2))
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Sección de perfil
            Text(
                text = stringResource(R.string.profile_title),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = Color.White,
                tonalElevation = 2.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .background(Color(0xFFE0E0E0), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = stringResource(R.string.profile_photo_desc),
                            tint = Color.Gray,
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Surface(
                        color = Color.Black,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.profile_role_moderator),
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(text = stringResource(R.string.profile_name_label), fontWeight = FontWeight.SemiBold)
                    Text(text = stringResource(R.string.profile_username_placeholder))
                    Text(text = stringResource(R.string.profile_city_label))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Sección de cuenta
            Text(
                text = stringResource(R.string.profile_account_title),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = Color.White,
                tonalElevation = 2.dp
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    ButtonItem(
                        icon = Icons.Default.Edit,
                        text = stringResource(R.string.profile_edit),
                        description = stringResource(R.string.profile_edit_desc)
                    )
                    Divider(modifier = Modifier.padding(vertical = 4.dp))
                    ButtonItem(
                        icon = Icons.Default.Lock,
                        text = stringResource(R.string.profile_change_password),
                        description = stringResource(R.string.profile_change_password_desc)
                    )
                    Divider(modifier = Modifier.padding(vertical = 4.dp))
                    ButtonItem(
                        icon = Icons.Outlined.Info,
                        text = stringResource(R.string.profile_help),
                        description = stringResource(R.string.profile_help_desc)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Info de la app
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = Color.White,
                tonalElevation = 2.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(R.string.app_name), fontWeight = FontWeight.Bold)
                    Text(text = stringResource(R.string.app_version))
                    Text(text = stringResource(R.string.app_description))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón de cerrar sesión
            Button(
                onClick = { /* acción de cerrar sesión */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE53935)),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(50.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = stringResource(R.string.profile_logout_desc),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    stringResource(R.string.profile_logout),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp)) // 👈 Último espacio para no cortar el botón
        }
    }
}

@Composable
fun ButtonItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    description: String
) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = text, tint = Color.Gray)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text, fontWeight = FontWeight.SemiBold)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = description, fontSize = 13.sp, color = Color.Gray)
    }
}
