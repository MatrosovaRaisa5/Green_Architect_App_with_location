package com.yt.greenarchitectapp.screens.geolocation

import android.Manifest
import android.content.Context
import android.location.Geocoder
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.Locale
import com.yt.greenarchitectapp.R
import com.yt.greenarchitectapp.ui.theme.orange

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationChoose(
    navController: NavHostController
) {

    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id=R.drawable.regfon),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize())

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 40.dp)

        ) {
            Spacer(modifier = Modifier.height(70.dp))
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(bottom = 150.dp)
                    .width(200.dp)
                    .height(200.dp),
            )
            Text(
                "Пожалуйста, выберите ваш город или регион",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight(700),
                color = Color.Black,
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
            )
        }
        Spacer(modifier = Modifier.height(0.dp))
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 10.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navController.navigate("citiesList") },
                modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF488038)
                )
            ) {
                Text(
                    text = "Выбрать",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
            }


            val permissions = listOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )

            val permissionState = rememberMultiplePermissionsState(permissions)
            var hasNavigated by remember { mutableStateOf(false) }

            if (permissionState.allPermissionsGranted) {
                LaunchedEffect(Unit) {
                    if (!hasNavigated)
                    {
                        getLocation(context, navController)
                        hasNavigated = true
                    }
                }
            }
            Button(
                onClick = { permissionState.launchMultiplePermissionRequest() },
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = orange
                )
            ) {
                Text(
                    text = "Определить автоматически",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

fun getLocation(context: Context, navController: NavHostController)
{
    val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    fusedLocationClient.lastLocation.addOnSuccessListener { location ->
        if (location != null) {
            val cityName = getCityName(location.latitude, location.longitude, context)
            navController.navigate("locationConfirm/${cityName}")
        } else {
            navController.navigate("locationConfirm/null")
        }
    }
}

private fun getCityName(latitude: Double, longitude: Double, context: Context): String? {
    val geocoder = Geocoder(context, Locale("ru"))
    val addresses = geocoder.getFromLocation(latitude, longitude, 1)
    return if (addresses != null) {
        addresses[0]?.locality
    } else {
        null
    }
}