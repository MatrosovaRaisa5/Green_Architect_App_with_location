package com.yt.greenarchitectapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.yt.greenarchitectapp.ui.theme.FoodDeliveryAppUiTheme

abstract class BaseActivity : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FoodDeliveryAppUiTheme() {
                    Content()
            }
        }
    }

    @Composable
    abstract fun Content()
}