package com.yt.greenarchitectapp.screens.geolocation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun LocationNavigation(
    navHostController: NavHostController,
) {
    NavHost(navController = navHostController, startDestination = "locationChoose") {
        composable("locationChoose") { LocationChoose(navHostController) }
        composable("locationConfirm/{cityName}") { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            LocationConfirm(cityName, navHostController)
        }
        composable("citiesList") { CitiesList(navHostController) }
    }
}


