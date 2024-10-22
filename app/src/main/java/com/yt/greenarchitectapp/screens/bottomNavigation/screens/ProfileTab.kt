package com.yt.greenarchitectapp.screens.bottomNavigation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.yt.greenarchitectapp.R
import com.yt.greenarchitectapp.commonui.*
import com.yt.greenarchitectapp.ui.theme.textColor1

@Composable
fun ProfileTab(
    navHostController: NavHostController
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color.White)
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
                                    Text18_600(text = "Раиса Матросова", color = Color.Black)
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
                    }

                }




            }

        }
    }
}