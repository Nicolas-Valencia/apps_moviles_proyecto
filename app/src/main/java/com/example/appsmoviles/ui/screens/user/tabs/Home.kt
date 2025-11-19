package com.example.appsmoviles.ui.screens.user.tabs

import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.appsmoviles.R
import com.example.appsmoviles.ui.components.Map
import com.mapbox.geojson.Point
import com.mapbox.maps.MapboxMap
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.plugin.annotation.generated.PointAnnotation
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.extension.compose.annotation.rememberIconImage
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.locationcomponent.createDefault2DPuck
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.maps.plugin.viewport.data.DefaultViewportTransitionOptions

@Composable
fun Home() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 120.dp)
    ) {
        Text(
            text = stringResource(R.string.txt_home_title),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // --- Sección Lugares Cercanos ---
        Map(
            modifier = Modifier
                .height(300.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- Última Búsqueda ---
        Text(
            text = stringResource(R.string.txt_last_search),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        PlaceCard(
            name = "Café Central",
            description = "Ambiente tranquilo y moderno.",
            status = "Cerrado"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- Lugares Favoritos ---
        Text(
            text = stringResource(R.string.txt_favorite_places),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        PlaceCard(
            name = "Restaurante El Roble",
            description = "Comida típica y postres.",
            status = "Abierto"
        )
    }
}

@Composable
fun rememberLocationPermissionState(
    permission: String = android.Manifest.permission.ACCESS_FINE_LOCATION,
    onPermissionResult: (Boolean) -> Unit
): Boolean {
    val context = LocalContext.current
    val permissionGranted = remember{
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
    ) { granted ->
        permissionGranted.value = granted
        onPermissionResult(granted)
    }

    LaunchedEffect(Unit) {
        if (!permissionGranted.value) {
            launcher.launch(permission)
        }
    }

    return permissionGranted.value
}

@Composable
fun PlaceCard(name: String, description: String, status: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF3F3F3)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = name,
                modifier = Modifier
                    .size(60.dp)
                    .background(Color(0xFFD0EAE2), RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = name, fontWeight = FontWeight.Bold)
                Text(text = description, fontSize = 12.sp, color = Color.Gray)
                Text(
                    text = stringResource(R.string.txt_distance_example),
                    fontSize = 12.sp
                )
            }
            Text(
                text = status,
                color = if (status == "Abierto") Color(0xFF2ECC71) else Color(0xFFE74C3C),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
    }
}
