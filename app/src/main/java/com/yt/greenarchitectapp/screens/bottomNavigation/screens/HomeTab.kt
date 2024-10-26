package com.yt.greenarchitectapp.screens.bottomNavigation.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yt.greenarchitectapp.R
import com.yt.greenarchitectapp.commonui.*
import com.yt.greenarchitectapp.model.Vegetables
import com.yt.greenarchitectapp.model.listOfPopular
import com.yt.greenarchitectapp.model.listOfVegetables
import com.yt.greenarchitectapp.screens.activities.CartActivity
import com.yt.greenarchitectapp.screens.activities.DetailActivity
import com.yt.greenarchitectapp.utils.launchActivity
import kotlinx.coroutines.launch

@Composable
fun HomeTab(
    scaffoldState: ScaffoldState
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val horizontalScrollState = rememberScrollState()
    val search = remember { mutableStateOf("") }
    val lists by remember { mutableStateOf(listOf("Овощи", "Цветы", "Плодово-ягодные", "Рассада")) }
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
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                CommonIconButton(icon = R.drawable.nav_bar) {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                CommonIconButton(icon = R.drawable.plant) {
                    context.launchActivity<CartActivity> { }
                }
            }
        }

        item {
            Text34_700(
                text = "Растения для Вас",
                color = Color.Black,
                modifier = Modifier.padding(start = 20.dp)
            )
        }

        item {
            Text22_700(
                text = "Популярное",
                color = Color.Black,
                modifier = Modifier.padding(start = 30.dp)
            )
        }

        item {
            PopularTabUi()
        }

        item {
            CommonSearchBar(
                text = search,
                isEnabled = true,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(horizontalScrollState)
                    .padding(vertical=10.dp, horizontal=10.dp)
            ) {
                lists.forEach {
                    TabBarListRow(
                        text = it,
                        selected = it == currentListValue,
                    ) {
                        currentListValue = it
                    }
                }
            }
        }

        item {
            CommonIconButton(icon = R.drawable.filter, modifier = Modifier.padding(start = 10.dp, bottom = 10.dp))
        }

        if (currentListValue == "Овощи") {
            if (listOfVegetables.isEmpty()) {
                item {
                    Text(text = "Пока что ничего тут нет...", color = Color.Red, modifier = Modifier.padding(start = 10.dp))
                }
            } else {
                items(listOfVegetables) { vegetable ->
                    EachRow(vegetables = vegetable) { selectedVegetable ->
                        val intent = Intent(context, DetailActivity::class.java).apply {
                            putExtra("data2", selectedVegetable)
                        }
                        context.startActivity(intent)
                    }
                }
                item { Spacer(modifier = Modifier.height(50.dp)) }
            }
        }
    }
}
@Composable
fun PopularTabUi() {
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

@Composable
fun EachRow(
    vegetables: Vegetables,
    onClick: (Vegetables) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 10.dp)
            .clickable { onClick(vegetables) },
        elevation = 1.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Box(modifier = Modifier.background(Color.White)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = vegetables.image), contentDescription = "",
                    modifier = Modifier.size(90.dp)
                )
                Column(
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(start = 10.dp)
                ) {
                    Text17_600(text = vegetables.name, color = Color.Black)

                }
            }
        }
    }
}