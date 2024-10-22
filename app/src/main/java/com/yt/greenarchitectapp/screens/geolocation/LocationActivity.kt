package com.yt.greenarchitectapp.screens.geolocation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.yt.greenarchitectapp.BaseActivity
import com.yt.greenarchitectapp.ui.theme.lightGray

class LocationActivity : BaseActivity() {

    @Composable
    override fun Content() {
        val navHostController = rememberNavController()
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LocationNavigation(navHostController = navHostController)
        }
    }
}