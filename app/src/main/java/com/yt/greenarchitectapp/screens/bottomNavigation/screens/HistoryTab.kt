package com.yt.greenarchitectapp.screens.bottomNavigation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.yt.greenarchitectapp.R

@Composable
fun HistoryTab() {
    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id= R.drawable.detailfon),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize())
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

    }

}