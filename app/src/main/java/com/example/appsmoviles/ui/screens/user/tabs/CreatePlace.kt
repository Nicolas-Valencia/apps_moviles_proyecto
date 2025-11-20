package com.example.appsmoviles.ui.screen

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
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
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import com.example.appsmoviles.model.Location
import com.example.appsmoviles.model.Place
import com.example.appsmoviles.model.PlaceType
import com.example.appsmoviles.ui.components.DropdownMenu
import com.example.appsmoviles.ui.components.Map
import com.example.appsmoviles.ui.components.TextFields
import com.mapbox.geojson.Point
import java.util.UUID
import androidx.core.content.ContextCompat
import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun CreatePlace(padding: PaddingValues = PaddingValues(0.dp)) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var clickedPoint by rememberSaveable { mutableStateOf<Point?>(null) }

    val config = mapOf(
        "cloud_name" to "dhjx9so9r",
        "api_key" to "845333576848746",
        "api_secret" to "va6752CDZfJDjYSdl0QVXRGzrFA"
    )

    val cloudinary = remember { Cloudinary(config) }

    var imageUrl by rememberSaveable { mutableStateOf("") }
    var isUploadingImage by remember { mutableStateOf(false) }

    val fileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            isUploadingImage = true
            scope.launch(Dispatchers.IO) {
                try {
                    val inputStream = context.contentResolver.openInputStream(it)
                    inputStream?.use { stream ->
                        val result = cloudinary.uploader().upload(stream, ObjectUtils.emptyMap())
                        val uploadedUrl = result["secure_url"].toString()

                        withContext(Dispatchers.Main) {
                            imageUrl = uploadedUrl
                            isUploadingImage = false
                            Toast.makeText(context, "Imagen subida correctamente", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        isUploadingImage = false
                        Toast.makeText(context, "Error al subir imagen: ${e.message}", Toast.LENGTH_SHORT).show()
                        Log.e("CreatePlace", "Error uploading image", e)
                    }
                }
            }
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.values.all { it }

        if (allGranted) {
            fileLauncher.launch("image/*")
        } else {
            Toast.makeText(context, "Permisos denegados", Toast.LENGTH_SHORT).show()
        }
    }

    var nombre by rememberSaveable { mutableStateOf("") }
    var descripcion by rememberSaveable { mutableStateOf("") }
    var direccion by rememberSaveable { mutableStateOf("") }
    var telefonos by rememberSaveable { mutableStateOf("") }
    var tipoSeleccionado by rememberSaveable { mutableStateOf("") }

    var nombreError by remember { mutableStateOf(false) }
    var descripcionError by remember { mutableStateOf(false) }
    var direccionError by remember { mutableStateOf(false) }
    var tipoError by remember { mutableStateOf(false) }

    val tiposLugar = listOf(
        "Restaurante",
        "Bar",
        "Hotel",
        "Parque",
        "Tienda",
        "Otros"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.txt_create_place),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )

        Text(
            text = stringResource(R.string.txt_basic_info),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        TextFields(
            value = nombre,
            label = stringResource(R.string.txt_name),
            supportingText = stringResource(R.string.txt_name_error),
            onValueChange = { nombre = it },
            onValidate = { it.isBlank() }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextFields(
            value = descripcion,
            label = stringResource(R.string.txt_description),
            supportingText = stringResource(R.string.txt_description_error),
            onValueChange = { descripcion = it },
            onValidate = { it.isBlank() }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextFields(
            value = direccion,
            label = "Dirección",
            supportingText = "La dirección es requerida",
            onValueChange = { direccion = it },
            onValidate = { it.isBlank() }
        )
        Spacer(modifier = Modifier.height(8.dp))

        DropdownMenu(
            label = "Tipo de lugar",
            list = tiposLugar,
            selectedItem = tipoSeleccionado,
            onValueChange = {
                tipoSeleccionado = it
                tipoError = false
            },
            isError = tipoError
        )
        if (tipoError) {
            Text(
                text = "Selecciona un tipo de lugar",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp, bottom = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Ubicación",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Map(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            activateClick = true,
            onMapClickListener = { l ->
                clickedPoint = l
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.txt_contact_info),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = telefonos,
            onValueChange = { telefonos = it },
            label = { Text("Teléfonos") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Botón para seleccionar imagen
        OutlinedButton(
            onClick = {
                val permissionCheckResult = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    ContextCompat.checkSelfPermission(context, Manifest.permission.READ_MEDIA_IMAGES)
                } else {
                    ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                }

                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                    fileLauncher.launch("image/*")
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        permissionLauncher.launch(arrayOf(Manifest.permission.READ_MEDIA_IMAGES))
                    } else {
                        permissionLauncher.launch(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE))
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isUploadingImage
        ) {
            if (isUploadingImage) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Subiendo imagen...")
            } else {
                Text(if (imageUrl.isNotEmpty()) "Cambiar imagen" else "Seleccionar imagen")
            }
        }

        // Mostrar URL de la imagen si existe
        if (imageUrl.isNotEmpty()) {
            Text(
                text = "Imagen seleccionada",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                nombreError = nombre.isBlank()
                descripcionError = descripcion.isBlank()
                direccionError = direccion.isBlank()
                tipoError = tipoSeleccionado.isBlank()

                if (!nombreError && !descripcionError && !direccionError && !tipoError) {
                    val placeType = when (tipoSeleccionado) {
                        "Restaurante" -> PlaceType.RESTAURANT
                        "Bar" -> PlaceType.BAR
                        "Hotel" -> PlaceType.HOLTEL
                        "Parque" -> PlaceType.PARK
                        "Tienda" -> PlaceType.SHOPPING
                        else -> PlaceType.OTHER
                    }

                    val place = Place(
                        id = UUID.randomUUID().toString(),
                        name = nombre,
                        description = descripcion,
                        address = direccion,
                        location = Location(clickedPoint!!.latitude(), clickedPoint!!.longitude()),
                        images = if (imageUrl.isNotBlank())
                            listOf(imageUrl)
                        else
                            emptyList(),
                        phones = if (telefonos.isNotBlank())
                            telefonos.split(",").map { it.trim() }
                        else
                            emptyList(),
                        schedule = emptyList(),
                        type = placeType
                    )

                    Log.d("CreatePlace", "Lugar creado: $place")
                    Toast.makeText(context, context.getString(R.string.txt_place_created), Toast.LENGTH_SHORT).show()

                    // Limpiar campos
                    nombre = ""
                    descripcion = ""
                    direccion = ""
                    imageUrl = ""
                    telefonos = ""
                    tipoSeleccionado = ""
                    clickedPoint = null
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            enabled = !isUploadingImage
        ) {
            Text(stringResource(R.string.txt_save), fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}