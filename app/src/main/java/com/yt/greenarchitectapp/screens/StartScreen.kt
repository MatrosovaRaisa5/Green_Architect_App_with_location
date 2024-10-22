package com.yt.greenarchitectapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.yt.greenarchitectapp.commonui.AnimatedText
import com.yt.greenarchitectapp.commonui.CommonButton
import com.yt.greenarchitectapp.commonui.Text65_800
import com.yt.greenarchitectapp.R

object StartScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.startfon),
                contentDescription = "Background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp),
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.icon),
                            contentDescription = "",
                            modifier = Modifier.size(73.dp),
                            tint = Color.Unspecified,
                        )

                        Text65_800(
                            text = "Green Architect",
                            color = Color.White,
                        )
                    }


                    Spacer(modifier = Modifier.height(240.dp))

                    AnimatedText(
                        text = "Садоводство без границ!",
                        color = Color.White,
                        delayBetweenChars = 100
                    )

                    Spacer(modifier = Modifier.height(240.dp))

                    Column {
                        CommonButton(text = "Вперед!") {
                            navigator += RegisterScreen
                        }
                    }
                }
            }
        }
    }
}

