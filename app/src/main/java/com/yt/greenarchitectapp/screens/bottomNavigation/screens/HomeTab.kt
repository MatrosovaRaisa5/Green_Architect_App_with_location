package com.yt.greenarchitectapp.screens.bottomNavigation.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yt.greenarchitectapp.R
import com.yt.greenarchitectapp.commonui.*
import com.yt.greenarchitectapp.model.listOfPopular
import com.yt.greenarchitectapp.model.listOfVegetables
import com.yt.greenarchitectapp.screens.activities.CartActivity
import com.yt.greenarchitectapp.screens.activities.DetailActivity
import com.yt.greenarchitectapp.utils.launchActivity
import kotlinx.coroutines.launch

@Composable
fun HomeTab(
    scaffoldState: ScaffoldState
)  {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val horizontalScrollState = rememberScrollState()
    val verticalScrollState = rememberScrollState()
    val search = remember { mutableStateOf("") }
    val lists by remember { mutableStateOf(listOf("Овощи", "Цветы", "Плодово-ягодные", "Рассада")) }
    var currentListValue by remember { mutableStateOf("Овощи") }

    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id=R.drawable.catfon),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize())
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(verticalScrollState)
            .padding(bottom = 50.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 20.dp),
        ) {
            CommonIconButton(icon = R.drawable.nav_bar){
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }
            CommonIconButton(icon = R.drawable.plant){
                context.launchActivity<CartActivity> {  }
            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top=10.dp,bottom = 10.dp, start = 30.dp)
        ) {
            Text34_700(text = "Растения для Вас", color = Color.Black)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top=15.dp, bottom = 10.dp,start = 40.dp)
        ) {
            Text22_700(text = "Популярное", color = Color.Black)
        }
        PopularTabUi()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 30.dp)
        ) {
            CommonSearchBar(
                text = search,
                isEnabled = true,
                modifier = Modifier.NoRippleEffect {
                })
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(horizontalScrollState)
                .padding(top = 10.dp, bottom = 10.dp, start = 30.dp)
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
        ) {
            CommonIconButton(icon = R.drawable.filter)
        }

        if (currentListValue == "Овощи")

            FoodTabUi(context)

    }
}


@Composable
fun PopularTabUi() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, bottom = 10.dp, end = 20.dp)
    ) {
        items(listOfPopular) { popular ->
            PopularEachRow(popular){
            }
        }
    }
}
@Composable
fun FoodTabUi(context:Context) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, bottom = 10.dp, end = 20.dp)
    ) {
        items(listOfVegetables) { vegetables ->
            FoodEachRow(vegetables){
                context.launchActivity<DetailActivity> {
                    putExtra("data2", vegetables)
                }
            }
        }
    }
}

