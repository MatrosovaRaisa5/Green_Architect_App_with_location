package com.yt.greenarchitectapp.screens.bottomNavigation.screens.functions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yt.greenarchitectapp.R
import com.yt.greenarchitectapp.commonui.CommonIconButton
import com.yt.greenarchitectapp.commonui.CommonSearchBar
import com.yt.greenarchitectapp.commonui.Text17_600
import com.yt.greenarchitectapp.commonui.Text34_700
import com.yt.greenarchitectapp.commonui.Text65_800
import com.yt.greenarchitectapp.ui.theme.orange

class LibraryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content()
        }
    }

    @Composable
    fun Content() {
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
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        //CommonIconButton(icon = R.drawable.back) {
                            //finish()
                        //}
                        Text34_700(
                            text = "Библиосад",
                            color = Color.Black,
                            modifier = Modifier.padding(top=20.dp, start=20.dp, end=10.dp)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.books),
                            contentDescription = "",
                            modifier = Modifier.size(60.dp).padding(top=20.dp),
                            tint = Color.Unspecified,
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text17_600(
                        text = "Интересно о садоводстве с нами!",
                        color = orange,
                        modifier = Modifier.padding(top=20.dp, start=28.dp)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
                item {
                    CommonSearchBar(
                        text = search,
                        isEnabled = true,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }

                item {
                    ArticleCard(
                        "Домашние растения: Узнайте, кто на самом деле правит вашим домом!",
                        R.drawable.picture1
                    )
                }
                item {
                    ArticleCard(
                        "Садоводство для начинающих: Как вырастить свой огород, не сойдя с ума?",
                        R.drawable.picture2
                    )
                }
            }
        }
    }}

    @Composable
    fun ArticleCard(title: String, imageRes: Int) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(10.dp)
                .clip(RoundedCornerShape(20.dp))
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f))
                    .clip(RoundedCornerShape(30.dp))
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "Читать статью >",
                    color = Color.White,
                    modifier = Modifier.padding(top = 20.dp, end = 20.dp),
                    textAlign = TextAlign.End,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.W600,
                )
                Text(
                    text = title,
                    color = Color.White,
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 16.dp),
                    textAlign = TextAlign.End,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.W600,
                )
            }
        }
    }