package com.example.appsmoviles.ui.screens.user.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appsmoviles.R

@Composable
fun Profile(
    padding: PaddingValues = PaddingValues(0.dp),
    onNavigateToEditAccount: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Foto de perfil",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color(0xFFDDEBF7))
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "@username",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Armenia, Quindío",
            color = Color.Gray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            UserStat("Lugares", "12")
            UserStat("Reseñas", "5")
            UserStat("Promedio", "4.8⭐")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            ProfileOption(
                icon = Icons.Default.Edit,
                text = stringResource(R.string.txt_edit_profile),
                onClick = onNavigateToEditAccount
            )
            ProfileOption(
                icon = Icons.Outlined.Lock,
                text = stringResource(R.string.txt_change_password),
                onClick = { /* Acción */ }
            )
            ProfileOption(
                icon = Icons.Outlined.LocationOn,
                text = stringResource(R.string.txt_my_places),
                onClick = { /* Acción */ }
            )
            ProfileOption(
                icon = Icons.Outlined.Info,
                text = stringResource(R.string.txt_help_support),
                onClick = { /* Acción */ }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { /* Acción de logout */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE74C3C)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(Icons.Default.Clear, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(stringResource(R.string.txt_logout))
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun UserStat(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text(text = label, color = Color.Gray, fontSize = 14.sp)
    }
}

@Composable
fun ProfileOption(icon: Any, text: String, onClick: () -> Unit = {}) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        when (icon) {
            is androidx.compose.ui.graphics.vector.ImageVector -> Icon(icon, contentDescription = null)
            is androidx.compose.ui.graphics.painter.Painter -> Icon(icon, contentDescription = null)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text)
    }
}