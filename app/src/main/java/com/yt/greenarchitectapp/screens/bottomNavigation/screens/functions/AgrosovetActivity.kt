package com.yt.greenarchitectapp.screens.bottomNavigation.screens.functions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yt.greenarchitectapp.R
import com.yt.greenarchitectapp.commonui.CommonButton
import com.yt.greenarchitectapp.commonui.Text17_600
import com.yt.greenarchitectapp.commonui.Text22_700
import com.yt.greenarchitectapp.commonui.Text34_700
import com.yt.greenarchitectapp.ui.theme.orange

class AgrosovetActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content()
        }
    }

    @Composable
    fun Content() {
        Surface {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.catfon),
                    contentDescription = "Background Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                LazyColumn(
                    modifier = Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    item {
                        Row {
                            Text34_700(
                                text = "Агросовет",
                                color = Color.Black,
                                modifier = Modifier.padding(top=20.dp,start=20.dp,end = 10.dp)
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.asovet),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(70.dp)
                                .padding(top=20.dp),
                                tint = Color.Unspecified,
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text17_600(
                            text = "Ваш ключ к успешному урожаю:",
                            color = orange,
                            modifier = Modifier.padding(top=20.dp, start=28.dp)
                        )
                        Text17_600(
                            text = "Получите профессиональные советы агронома",
                            color = orange,
                            modifier = Modifier.padding(top=20.dp, start=28.dp)
                        )
                    }
                    item {
                        Text(
                            text = "Специалисты",
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.W600,
                            color = Color(0xFF29852E),
                            modifier = Modifier.padding(top = 20.dp, start = 28.dp)
                        )
                    }
                    item { AgronomistCard(R.drawable.agronom1, "Ольга Васильева",
                        "Приветствую всех! Я агроном с 11-летним стажем и настоящая любительница цветов. Моя страсть — это розы! Я занимаюсь их разведением и знаю, как сделать так, чтобы они радовали вас своим цветением. Если вы хотите научиться ухаживать за цветами или выбрать подходящие сорта для вашего сада, я с удовольствием помогу вам!",
                        288, 456)
                    }
                    item { AgronomistCard(R.drawable.agronom2, "Сергей Иванов",
                        "Здравствуйте, друзья! Я агроном с 9-летним опытом, и моя любовь — это томаты! Я разводил множество сортов, включая знаменитые бычье сердце, воловье сердце, кенигсберг и другие. Знаю, как вырастить вкусные и сочные помидоры, и готов поделиться секретами их ухода. Если вы хотите получить отличный урожай, обращайтесь — помогу вам выбрать лучшие сорта и научу, как за ними ухаживать!",
                        149, 576)
                    }
                }
            }
        }
    }
}

@Composable
fun AgronomistCard(imageResId: Int, name: String, description: String, articles: Int, answers: Int) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        modifier = Modifier.padding(10.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = name,
                modifier = Modifier.size(160.dp).align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(9.dp))
            Text22_700(text = name, color = Color.Black)
            Spacer(modifier = Modifier.height(7.dp))
            Text17_600(text = description, color = Color.Gray)
            Spacer(modifier = Modifier.height(15.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text17_600(text = "Статьи: $articles", color = Color.Black)
                Text17_600(text = "Ответы: $answers", color = Color.Black)
            }
            Spacer(modifier = Modifier.height(8.dp))
            CommonButton(
                text = "Написать",
                backgroundColor = orange,
                foregroundColor = Color.White,
                onClick = {  }
            )
        }
    }
}