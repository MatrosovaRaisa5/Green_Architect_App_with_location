package com.yt.greenarchitectapp.screens.bottomNavigation.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.google.android.gms.location.LocationServices
import com.yt.greenarchitectapp.R
import com.yt.greenarchitectapp.commonui.*
import com.yt.greenarchitectapp.screens.activities.GreyActivity
import com.yt.greenarchitectapp.screens.activities.RedActivity
import com.yt.greenarchitectapp.screens.activities.getCityFromPreferences
import com.yt.greenarchitectapp.screens.activities.getNurseriesByCity
import com.yt.greenarchitectapp.ui.theme.orange
import com.yt.greenarchitectapp.ui.theme.textColor1
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@Composable
fun ProfileTab(
    navHostController: NavHostController
) {
    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id=R.drawable.detailfon),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize())
    }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                CommonHeader(
                    text = "Мой профиль",
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
                ) {
                    navHostController.navigateUp()
                }


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp, vertical = 10.dp)
                ) {
                    Text17_600(
                        text = "Информация",
                        color = Color.Black,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = 2.dp,
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Box(modifier = Modifier.background(Color.White)) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.pic),
                                    contentDescription = "",
                                    modifier = Modifier.size(60.dp)
                                )
                                Column(
                                    modifier = Modifier.padding(start = 20.dp)
                                ) {
                                    Text18_600(text = "Великий садовод", color = Color.Black)
                                    Text13_400(text = "minteee@list.ru", color = textColor1)

                                }
                            }

                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 30.dp, vertical = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        TextLocation(
                            text = "Местоположение",
                            color = Color.Black,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                        MapViewComposable()
                        Spacer(modifier = Modifier.height(10.dp))

                    }


                }




            }

        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun MapViewComposable() {
    val nurseries = mapOf(
        "Москва" to listOf(
            GeoPoint(55.773244228375574, 37.55728080249214),
            GeoPoint(55.633938150400894, 37.798706619822816),
            GeoPoint(55.932191342948514, 37.42891024767718)
        ),
        "Санкт-Петербург" to listOf(
            GeoPoint(59.79381706087419, 30.316996823629868),
            GeoPoint(59.93515538859792, 30.512004150437345),
            GeoPoint(60.086853248909016, 30.355448972577822)
        ),
        "Новосибирск" to listOf(
            GeoPoint(55.016894006030505, 82.87536844714712),
            GeoPoint(54.96842797053629, 82.90498588092586),
            GeoPoint(54.945009620174375, 82.84720760847225)
        ),
        "Омск" to listOf(
            GeoPoint(54.972125789391, 73.39055907014409),
            GeoPoint(54.962556884258674, 73.4771978171679),
            GeoPoint(55.01377487185413, 73.36455348027137)
        ),
        "Казань" to listOf(
            GeoPoint(55.80451021347444, 49.260501618480916),
            GeoPoint(55.81619075153829, 49.05234972825961),
            GeoPoint(55.80256311633008, 49.21055671278288)
        ),
        "Уфа" to listOf(
            GeoPoint(54.72417122774333, 55.968857006642956),
            GeoPoint(54.74161579833119, 55.99110836840032),
            GeoPoint(54.780249976962786, 56.04030633338678)
        ),
        "Нижний Новгород" to listOf(
            GeoPoint(56.320655999614345, 43.999113561290216),
            GeoPoint(56.24382568693887, 43.97543321299798),
            GeoPoint(56.245640570534896, 43.86723851821447)
        ),
        "Пермь" to listOf(
            GeoPoint(57.98704839587042, 56.20485623439847),
            GeoPoint(57.95976724350686, 56.26773164193303),
            GeoPoint(57.93680034568467, 56.059507889708165)
        ),
        "Краснодар" to listOf(
            GeoPoint(45.02823214885414, 38.97344062426599),
            GeoPoint(45.058273647462805, 39.01422792878554),
            GeoPoint(45.11202763988643, 39.093396676787584)
        ),
        "Владивосток" to listOf(
            GeoPoint(43.22946988380034, 131.9837644190506),
            GeoPoint(43.28520750977789, 131.99109691356503),
            GeoPoint(43.31489115180396, 131.9956797226366)
        ),
        "Ростов-на-Дону" to listOf(
            GeoPoint(47.20936193423359, 39.61578343029813),
            GeoPoint(47.21799061242886, 39.74281285092976),
            GeoPoint(47.241770509442695, 39.7623822481622)
        ),
        "Челябинск" to listOf(
            GeoPoint(55.13647574568248, 61.51826422134271),
            GeoPoint(55.17655819007749, 61.388807321075646),
            GeoPoint(55.18532557954555, 61.34300764139679)
        ),
        "Екатеринбург" to listOf(
            GeoPoint(59.9342802, 30.3350986),
            GeoPoint(59.9342802, 30.3350986),
            GeoPoint(59.95, 30.31)
        )
    )
    val context = LocalContext.current
    val savedCity = getCityFromPreferences(context)
    val cityCoordinates = mapOf(
        "Москва" to GeoPoint(55.7558, 37.6173),
        "Санкт-Петербург" to GeoPoint(59.9343, 30.3351),
        "Новосибирск" to GeoPoint(55.0084, 82.0155),
        "Екатеринбург" to GeoPoint(56.8389, 60.6057),
        "Нижний Новгород" to GeoPoint(56.3269, 44.0075),
        "Казань" to GeoPoint(55.8304, 49.0661),
        "Челябинск" to GeoPoint(55.1644, 61.4368),
        "Омск" to GeoPoint(54.9920, 73.3686),
        "Ростов-на-Дону" to GeoPoint(47.2225, 39.7182),
        "Уфа" to GeoPoint(54.7388, 55.9721),
        "Пермь" to GeoPoint(58.0104, 56.2500),
        "Краснодар" to GeoPoint(45.0355, 38.9753),
        "Владивосток" to GeoPoint(43.1156, 131.8855),
        "Норильск" to GeoPoint(69.3558, 88.1893)
    )
    val cityLocation = cityCoordinates[savedCity] ?: GeoPoint(55.7558, 37.6173)
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    val hasLocationPermissions = remember {
        ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    var userLocation by remember { mutableStateOf<GeoPoint?>(null) }

    LaunchedEffect(hasLocationPermissions) {
        if (hasLocationPermissions) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    userLocation = GeoPoint(location.latitude, location.longitude)
                }
            }
        }
    }

    val mapView = remember { MapView(context) }
    val initialLocation = userLocation ?: cityLocation

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .border(BorderStroke(2.dp, Color.DarkGray))
            .clip(RoundedCornerShape(8.dp))
    ) {
        AndroidView(factory = {
            mapView.apply {
                Configuration.getInstance().load(context, context.getSharedPreferences("prefs", 0))
                val mapController = this.controller
                mapController.setZoom(14.0)
                controller.setCenter(initialLocation)
                setMultiTouchControls(true)
                val markerPosition = userLocation ?: cityLocation
                val userLocationMarker = Marker(this).apply {
                    position = markerPosition
                    title = "Вы здесь"
                    setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                    icon = context.resources.getDrawable(R.drawable.housemarker, context.theme)
                }
                overlays.add(userLocationMarker)
                this.controller.setCenter(markerPosition)
                invalidate()
            }
        }, modifier = Modifier.fillMaxSize())
        LaunchedEffect(userLocation) {
            userLocation?.let { location ->
                mapView.overlays.forEach { overlay ->
                    if (overlay is Marker && overlay.title == "Вы здесь") {
                        overlay.position = location
                        mapView.controller.setCenter(location)
                        mapView.invalidate()
                    }
                }
            }
        }
    }
}