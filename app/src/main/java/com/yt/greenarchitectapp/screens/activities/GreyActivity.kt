package com.yt.greenarchitectapp.screens.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.material.Surface
import androidx.compose.ui.tooling.preview.Preview

class GreyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                Text(text = "Страничка GreyActivity")
            }
        }
    }
}