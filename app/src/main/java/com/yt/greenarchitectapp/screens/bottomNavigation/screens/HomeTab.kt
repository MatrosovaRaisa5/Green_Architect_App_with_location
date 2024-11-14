package com.yt.greenarchitectapp.screens.bottomNavigation.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yt.greenarchitectapp.R
import com.yt.greenarchitectapp.commonui.*
import com.yt.greenarchitectapp.model.Popular
import com.yt.greenarchitectapp.model.Vegetables
import com.yt.greenarchitectapp.model.listOfPopular
import com.yt.greenarchitectapp.model.listOfVegetables
import com.yt.greenarchitectapp.screens.activities.CartActivity
import com.yt.greenarchitectapp.screens.activities.DetailActivity
import com.yt.greenarchitectapp.screens.bottomNavigation.screens.note.NoteActivity
import com.yt.greenarchitectapp.ui.theme.orange
import com.yt.greenarchitectapp.utils.launchActivity
import kotlinx.coroutines.launch


@Composable
fun HomeTab(
    scaffoldState: ScaffoldState
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val search = remember { mutableStateOf("") }
    val lists by remember { mutableStateOf(listOf("Овощи", "Комнатные растения", "Цветы", "Плодово-ягодные", "Рассада")) }
    var currentListValue by remember { mutableStateOf("Овощи") }

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
        //item {
        // Row(
        //modifier = Modifier
        // .fillMaxWidth()
        // .padding(start = 10.dp, top = 10.dp, bottom = 10.dp),
        //horizontalArrangement = Arrangement.SpaceBetween
        //) {
        //Row {
        //CommonIconButton(icon = R.drawable.nav_bar) {
        //scope.launch {
        // scaffoldState.drawerState.open()
        // }
        //}
        // Spacer(modifier = Modifier.width(10.dp))
        //}
        // }
        // }




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
                    context.launchActivity<NoteActivity> { }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            GardenTasksRow()
        }
        item {
            Text22_700(
                text = "Спецпредложения",
                color = orange,
                modifier = Modifier.padding(start = 30.dp)
            )
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

        // если еще что-то надо будет
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
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, bottom = 10.dp, end = 20.dp)
    ) {
        item {
            TaskCard("Форум садоводов")
        }
        item {
            TaskCard("Лунный календарь")
        }
        item {
            TaskCard("Агросовет")
        }
        item {
            TaskCard("Библиотека садовода")
        }
        item {
            TaskCard("Менеджер грядок")
        }
        item {
            TaskCard("Сканер растений")
        }

    }
}

@Composable
fun TaskCard(
    title: String
) {
    Card(
        elevation = 2.dp,
        shape = RoundedCornerShape(80.dp),
        modifier = Modifier
            .padding(10.dp)
            .size(110.dp)
            .border(3.dp, Color(0xFFA4C897), RoundedCornerShape(80.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5FFF0))
                .clickable { /* Обработчик нажатий */ }
        ) {
            Text(
                text = title,
                color = Color.Black,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Center)
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
        modifier = Modifier
            .padding(10.dp)
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
                    Modifier.size(135.dp)
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

@Composable
fun Functions() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, bottom = 10.dp, end = 20.dp)
    ) {
        items(listOfPopular) { popular ->
            PopularEachRow(popular) {}
        }
    }
}
