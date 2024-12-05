package com.yt.greenarchitectapp.screens.bottomNavigation.screens

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner

import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yt.greenarchitectapp.R
import com.yt.greenarchitectapp.commonui.CommonIconButton
import com.yt.greenarchitectapp.commonui.CommonSearchBar
import com.yt.greenarchitectapp.commonui.TabBarListRow
import com.yt.greenarchitectapp.commonui.Text17_600
import com.yt.greenarchitectapp.commonui.Text34_700
import com.yt.greenarchitectapp.screens.activities.CartActivity
import com.yt.greenarchitectapp.model.Vegetables
import com.yt.greenarchitectapp.model.listOfVegetables
import com.yt.greenarchitectapp.screens.activities.DetailActivity
import com.yt.greenarchitectapp.ui.theme.orange
import com.yt.greenarchitectapp.utils.launchActivity

@Composable
fun CatalogTab(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val horizontalScrollState = rememberScrollState()
    val search = remember { mutableStateOf("") }
    val lists by remember { mutableStateOf(listOf("Овощи", "Комнатные растения", "Цветы", "Плодово-ягодные", "Рассада")) }
    var currentListValue by remember { mutableStateOf("Овощи") }
    var showFilterMenu by remember { mutableStateOf(false) } // State for filter menu visibility
    var filteredVegetables by remember { mutableStateOf(listOfVegetables) }

    // Update filteredVegetables whenever the search or current list changes
    LaunchedEffect(search.value, currentListValue) {
        filteredVegetables = listOfVegetables.filter { vegetable ->
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
                Spacer(modifier = Modifier.height(2.dp))
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
                        .padding(vertical = 10.dp, horizontal = 10.dp)
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
                Box {
                    CommonIconButton(
                        icon = R.drawable.filter,
                        modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
                    ) {
                        showFilterMenu = !showFilterMenu
                    }
                    DropdownMenu(
                        expanded = showFilterMenu,
                        onDismissRequest = { showFilterMenu = false }
                    ) {
                        DropdownMenuItem(onClick = {
                            filteredVegetables = filteredVegetables.sortedBy { it.name };
                            showFilterMenu = false
                        }) {
                            Text("От А до Я")
                        }
                    }
                }
            }
            if (currentListValue == "Овощи") {
                if (filteredVegetables.isEmpty()) {
                    item {
                        Text(
                            text = "Ничего не нашли...",
                            color = orange,
                            modifier = Modifier.padding(start = 10.dp)
                        )
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