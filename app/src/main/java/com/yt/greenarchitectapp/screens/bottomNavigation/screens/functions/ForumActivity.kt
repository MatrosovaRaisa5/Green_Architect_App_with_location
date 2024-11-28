package com.yt.greenarchitectapp.screens.bottomNavigation.screens.functions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.yt.greenarchitectapp.R
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.ai.client.generativeai.Chat
import com.yt.greenarchitectapp.commonui.CommonHeader
import com.yt.greenarchitectapp.commonui.CommonIconButton
import com.yt.greenarchitectapp.commonui.CommonSearchBar
import com.yt.greenarchitectapp.commonui.Text22_700
import com.yt.greenarchitectapp.commonui.Text34_700
import com.yt.greenarchitectapp.screens.RegisterScreen.Content
import com.yt.greenarchitectapp.screens.activities.CartActivity
import com.yt.greenarchitectapp.screens.bottomNavigation.screens.launchActivity
import com.yt.greenarchitectapp.screens.notes.NoteActivity
import com.yt.greenarchitectapp.utils.launchActivity



class ForumActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("home") { HomeScreen(navController) }
                composable("second") { SecondScreen(navController) }
            }
        }
    }
    @Composable
    fun HomeScreen(navController: NavController) {
        Surface {
            val search = remember { mutableStateOf("") }
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.catfon),
                    contentDescription = "Background Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, start = 30.dp, end = 30.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        CommonIconButton(icon = R.drawable.back) {
                            finish()
                        }
                        CommonIconButton(icon = R.drawable.mail){
                            navController.navigate("second")
                        }

                    }

                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 70.dp, end = 15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text22_700(
                            text = "Форум Green Architect -",
                            color = Color(0xFF437039),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 40.dp, end = 15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text22_700(
                            text = "общаемся и обмениваемся",
                            color = Color(0xFF437039),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 142.dp, end = 15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text22_700(
                            text = "опытом!",
                            color = Color(0xFF437039),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                item {
                    CommonSearchBar(
                        text = search,
                        isEnabled = true,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }
                item{
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .background(Color.White)
                            .clip(RoundedCornerShape(8.dp)),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text22_700(
                            text = "Основной раздел",
                            color = Color(0xFF437039),
                            modifier = Modifier.padding(start = 90.dp, end = 15.dp),
                        )
                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(onClick = {}, colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF7FA869)), modifier = Modifier.fillMaxWidth()) {
                                Text("Новости и информация", color = Color.White)
                            }
                            Button(onClick = {},  colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF7FA869)),  modifier = Modifier.fillMaxWidth()) {
                                Text("Технический раздел", color = Color.White)
                            }
                            Button(onClick = {},  colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF7FA869)), modifier = Modifier.fillMaxWidth()) {
                                Text("Мероприятия и промокоды", color = Color.White)
                            }
                        }
                    }
                }
                item{
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .background(Color.White)
                            .clip(RoundedCornerShape(8.dp)),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text22_700(
                            text = "Общение",
                            color = Color(0xFF437039),
                            modifier = Modifier.padding(start = 125.dp, end = 15.dp),
                        )
                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(onClick = {}, colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF7FA869)), modifier = Modifier.fillMaxWidth()) {
                                Text("Советы и консультации", color = Color.White)
                            }
                            Button(onClick = {}, colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF7FA869)), modifier = Modifier.fillMaxWidth()) {
                                Text("Садовое оборудование и инвентарь", color = Color.White)
                            }
                            Button(onClick = {},  colors = ButtonDefaults.buttonColors(backgroundColor = Color(
                                0xFF7FA869
                            )
                            ),  modifier = Modifier.fillMaxWidth()) {
                                Text("Фотогалерея", color = Color.White)
                            }
                            Button(onClick = {},  colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF7FA869)),  modifier = Modifier.fillMaxWidth()) {
                                Text("Конкурсы", color = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }
    @Composable
    fun SecondScreen(navController: NavController) {
        Surface {
            val search = remember { mutableStateOf("") }
            val chats = listOf(
                Chat("Цветочная Фея", "Привет, как у тебя с урожаем в этом году? ", R.drawable.avatar1),
                Chat("pet_parent", "Как защитить растения от животных? Моя кошка любит ковыряться в земле☹", R.drawable.avatar5),
                Chat("Зелёный Перец", "Спасибоооо за ответ(づ ◕‿◕ )づ", R.drawable.avatar2),
                Chat("green_goddess", "Тебе случайно не нужны огурцы? У меня ооочень много уродилось", R.drawable.avatar4),
                Chat("Цветочный Кулинар", "Видела ваш новый пост, очень красивый и ухоженный сад!", R.drawable.avatar3)
            )
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.catfon),
                    contentDescription = "Background Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, start = 30.dp, end = 30.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        CommonIconButton(icon = R.drawable.back) {
                            navController.popBackStack()
                        }
                        CommonIconButton(icon = R.drawable.setting){

                        }

                    }

                }
                item {
                    CommonSearchBar(
                        text = search,
                        isEnabled = true,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }

                items(chats) { chat ->
                    ChatItem(chat)
                }
            }
        }
    }
    @Composable
    fun ChatItem(chat: Chat) {
        Card(
            backgroundColor = Color.White,
            elevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable { },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = chat.avatarResId),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(40.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text22_700(
                        text = chat.title,
                        color = Color(0xFF437039),
                        modifier = Modifier.padding(start = 30.dp, end = 15.dp),
                    )
                    Text(text = chat.lastMessage, style = MaterialTheme.typography.body2)
                }
            }
        }
    }

    data class Chat(val title: String, val lastMessage: String, val avatarResId: Int)
    inline fun <reified T> Context.launchActivity() {
        val intent = Intent(this, T::class.java)
        startActivity(intent)
    }
}

