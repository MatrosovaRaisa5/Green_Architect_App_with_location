package com.yt.greenarchitectapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.yt.greenarchitectapp.R
import com.yt.greenarchitectapp.commonui.*
import com.yt.greenarchitectapp.screens.activities.HomeActivity
import com.yt.greenarchitectapp.screens.geolocation.LocationActivity
import com.yt.greenarchitectapp.ui.theme.orange
import com.yt.greenarchitectapp.utils.launchActivity

object RegisterScreen : Screen {

    @Composable
    override fun Content() {

        val context = LocalContext.current
        var track1 by remember { mutableStateOf(true) }
        var track2 by remember { mutableStateOf(false) }
        val emailLogin = remember { mutableStateOf("") }
        val passwordLogin = remember { mutableStateOf("") }

        val emailReg = remember { mutableStateOf("") }
        val passwordReg = remember { mutableStateOf("") }


        Box(modifier = Modifier.fillMaxSize()){
            Image(painter = painterResource(id=R.drawable.regfon),
                contentDescription = "Background Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize())
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.4f),

                shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
                backgroundColor = Color.Transparent
            ) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(40.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.icon),
                        contentDescription = "",
                        modifier = Modifier.size(200.dp),
                        tint = Color.Unspecified
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column(
                            modifier = Modifier.width(90.dp)
                        ) {
                            Text18_600(text = "Войти", color = Color(0xFF233000), modifier = Modifier
                                .NoRippleEffect {
                                    if (track1) {
                                    } else {
                                        track1 = true
                                        track2 = false
                                    }
                                }
                                .align(CenterHorizontally))
                            if (track1)
                                CommonLine(
                                    height = 3.dp,
                                    width = 88.dp,
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                        }

                        Column(
                            modifier = Modifier.width(200.dp)
                        ) {
                            Text18_600(text = "Регистрация", color = Color(0xFF233000), modifier = Modifier
                                .NoRippleEffect {
                                    if (track2) {
                                    } else {
                                        track1 = false
                                        track2 = true
                                    }
                                }
                                .align(CenterHorizontally))
                            if (track2)
                                CommonLine(
                                    height = 3.dp,
                                    width = 198.dp,
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                        }
                    }


                }

            }

            if (track1)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                        .weight(0.6f),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    EmailAndPassword(email = emailLogin, password = passwordLogin) {
                        context.launchActivity<LocationActivity> { }
                    }
                }



            if (track2)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                        .weight(0.6f),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    EmailAndPassword(
                        email = emailReg,
                        password = passwordReg,
                        buttonText = "Зарегистрироваться",
                        isForget = false
                    ) {
                        context.launchActivity<LocationActivity> { }
                    }

                }
        }

    }

    @Composable
    fun EmailAndPassword(
        email: MutableState<String>,
        password: MutableState<String>,
        isForget: Boolean = true,
        buttonText: String = "Войти",
        onclick: () -> Unit
    ) {

        val focusRequester = FocusRequester()

        LaunchedEffect(key1 = Unit){
            focusRequester.requestFocus()
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Text15_600(text = "Email", color = Color(0xFF4E6B00))
                CommonTextField(text = email, modifier = Modifier.focusRequester(focusRequester))
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Text15_600(text = "Пароль", color = Color(0xFF4E6B00))
                CommonTextField(
                    text = password,
                    keyboardType = KeyboardType.Password,
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            if (isForget)
                Text17_600(text = "Забыли пароль?", color = orange)

        }

        CommonButton(
            text = buttonText,
            backgroundColor = orange,
            foregroundColor = Color.White
        ) {
            onclick()
        }


    }
}
