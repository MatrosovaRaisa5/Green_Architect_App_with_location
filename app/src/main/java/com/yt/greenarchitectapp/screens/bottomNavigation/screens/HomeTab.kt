package com.yt.greenarchitectapp.screens.bottomNavigation.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yt.greenarchitectapp.R
import com.yt.greenarchitectapp.commonui.*
import com.yt.greenarchitectapp.model.Popular
import com.yt.greenarchitectapp.model.listOfPopular
import com.yt.greenarchitectapp.screens.bottomNavigation.screens.functions.AgrosovetActivity
import com.yt.greenarchitectapp.screens.bottomNavigation.screens.functions.ForumActivity
import com.yt.greenarchitectapp.screens.bottomNavigation.screens.functions.GardenManagerActivity
import com.yt.greenarchitectapp.screens.bottomNavigation.screens.functions.LibraryActivity
import com.yt.greenarchitectapp.screens.bottomNavigation.screens.functions.LunarCalendarActivity
import com.yt.greenarchitectapp.screens.bottomNavigation.screens.functions.PlantScannerActivity
import com.yt.greenarchitectapp.screens.notes.NoteActivity
import com.yt.greenarchitectapp.ui.theme.orange

// Extension function to launch an activity from a context.
inline fun <reified T> Context.launchActivity() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

@Composable
fun HomeTab(
    scaffoldState: ScaffoldState
) {
    val context = LocalContext.current

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
            Text34_700(
                text = "Добро пожаловать!",
                color = Color.Black,
                modifier = Modifier.padding(top=20.dp,start = 28.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text22_700(
                    text = "Мои огородные дела",
                    color = Color(0xFF437039),
                    modifier = Modifier.weight(1f)
                )

                CommonIconButton(icon = R.drawable.notice) {
                    context.launchActivity<NoteActivity>()
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            GardenTasksRow()
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButtonN(icon = R.drawable.spof) { }
                Spacer(modifier = Modifier.width(5.dp))
                Text22_700(
                    text = "Спецпредложения",
                    color = orange,
                    modifier = Modifier.align(CenterVertically)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        // Первые две карточки
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PopularEachRow(popular = listOfPopular[0])
                PopularEachRow(popular = listOfPopular[1])
            }
        }

        // Вторые две карточки
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PopularEachRow(popular = listOfPopular[2])
                PopularEachRow(popular = listOfPopular[3])
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

        // Рекламка
        item {
            Text22_700(
                text = "Тут будет реклама...",
                color = Color.Gray,
                modifier = Modifier.padding(start = 30.dp)
            )
            Spacer(modifier = Modifier.height(120.dp))
        }
    }
}

@Composable
fun GardenTasksRow() {
    val context = LocalContext.current

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, bottom = 10.dp, end = 20.dp)
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.forum),
                contentDescription = "Форум садоводов",
                modifier = Modifier
                    .padding(10.dp)
                    .size(110.dp)
                    .clickable { context.launchActivity<ForumActivity>() }
            )
        }
        item {
            Image(
                painter = painterResource(id = R.drawable.lunar_calendar),
                contentDescription = "Лунный календарь",
                modifier = Modifier
                    .padding(10.dp)
                    .size(110.dp)
                    .clickable { context.launchActivity<LunarCalendarActivity>() }
            )
        }
        item {
            Image(
                painter = painterResource(id = R.drawable.agrosovet),
                contentDescription = "Агросовет",
                modifier = Modifier
                    .padding(10.dp)
                    .size(110.dp)
                    .clickable { context.launchActivity<AgrosovetActivity>() }
            )
        }
        item {
            Image(
                painter = painterResource(id = R.drawable.library),
                contentDescription = "Библиотека садовода",
                modifier = Modifier
                    .padding(10.dp)
                    .size(110.dp)
                    .clickable { context.launchActivity<LibraryActivity>() }
            )
        }
        item {
            Image(
                painter = painterResource(id = R.drawable.garden_manager),
                contentDescription = "Менеджер грядок",
                modifier = Modifier
                    .padding(10.dp)
                    .size(110.dp)
                    .clickable { context.launchActivity<GardenManagerActivity>() }
            )
        }
        item {
            Image(
                painter = painterResource(id = R.drawable.plant_scanner),
                contentDescription = "Сканер растений",
                modifier = Modifier
                    .padding(10.dp)
                    .size(110.dp)
                    .clickable { context.launchActivity<PlantScannerActivity>() }
            )
        }
    }
}

@Composable
fun PopularEachRow(
    popular: Popular,
    onClick: () -> Unit = {}
) {
    Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFFA4C897))
                .clickable { onClick() }
        ) {
            Column(
                horizontalAlignment = CenterHorizontally,
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 15.dp)
            ) {
                Image(
                    painter = painterResource(id = popular.image),
                    contentDescription = "",
                    modifier = Modifier.size(135.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text22_600(
                    text = popular.name,
                    color = Color.Black,
                    modifier = Modifier.align(CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}