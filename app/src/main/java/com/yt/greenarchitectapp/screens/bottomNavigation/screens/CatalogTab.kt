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
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.yt.greenarchitectapp.R
import com.yt.greenarchitectapp.commonui.*
import com.yt.greenarchitectapp.model.Vegetables
import com.yt.greenarchitectapp.model.listOfPopular
import com.yt.greenarchitectapp.model.listOfVegetables
import com.yt.greenarchitectapp.screens.activities.CartActivity
import com.yt.greenarchitectapp.screens.activities.DetailActivity
import com.yt.greenarchitectapp.ui.theme.orange
import com.yt.greenarchitectapp.utils.launchActivity
import kotlinx.coroutines.launch

@Composable
fun CatalogTab(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val horizontalScrollState = rememberScrollState()
    val search = remember { mutableStateOf("") }
    val lists by remember { mutableStateOf(listOf("Овощи", "Цветы", "Плодово-ягодные", "Рассада")) }
    var currentListValue by remember { mutableStateOf("Овощи") }
    val filteredVegetables = remember(search.value, currentListValue) {
        listOfVegetables.filter { vegetable ->
            vegetable.name.contains(search.value, ignoreCase = true)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.catfon),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

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
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        CommonIconButton(icon = R.drawable.back) {
                            navController.popBackStack()
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        CommonIconButton(icon = R.drawable.plant) {
                            context.launchActivity<CartActivity> { }
                        }
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
                if (filteredVegetables.isEmpty()) {
                    item {
                        Text(text = "Ничего не нашли...", color = orange, modifier = Modifier.padding(start = 10.dp))
                    }
                } else {
                    items(filteredVegetables) { vegetable ->
                        EachRowCat(vegetables = vegetable) { selectedVegetable ->
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
}

@Composable
fun EachRowCat(
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
                    modifier = Modifier.size(70.dp)
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