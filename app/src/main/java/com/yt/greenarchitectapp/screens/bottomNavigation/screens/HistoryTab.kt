package com.yt.greenarchitectapp.screens.bottomNavigation.screens

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.yt.greenarchitectapp.R
import com.yt.greenarchitectapp.model.Vegetables
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yt.greenarchitectapp.commonui.CommonIconButton
import com.yt.greenarchitectapp.commonui.Text17_600
import com.yt.greenarchitectapp.commonui.Text34_700
import com.yt.greenarchitectapp.screens.activities.CartActivity
import com.yt.greenarchitectapp.screens.activities.DetailActivity
import com.yt.greenarchitectapp.utils.launchActivity

@Composable
fun HistoryTab(
    navController: NavHostController
) {
    val context = LocalContext.current
    val historyManager = HistoryManager(context)

    var historyList by remember { mutableStateOf(listOf<Vegetables>()) }

    LaunchedEffect(Unit) {
        historyList = historyManager.getVegetableHistory()
    }

    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id= R.drawable.detailfon),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize())
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
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
                    text = "Вы смотрели",
                    color = Color.Black,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                        .padding(10.dp)
                ) {
                    Text17_600(
                        text = "Внимание! Просмотренные товары сохраняются в истории на 1 день, потом история очищается!",
                        color = Color.Black
                    )
                }
            }

            items(historyList) { vegetable ->
                EachRowCat(
                    vegetables = vegetable
                ) { selectedVegetable ->
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra("data2", selectedVegetable)
                    }
                    context.startActivity(intent)
                    historyManager.saveVegetableToHistory(selectedVegetable)
                    historyList = historyManager.getVegetableHistory()
                }
            }

            if (historyList.isEmpty()) {
                item {
                    Text34_700(
                        text = "История пуста",
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                    )
                }
            }
        }
    }
}

class HistoryManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("vegetable_history", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun getVegetableHistory(): List<Vegetables> {
        val json = sharedPreferences.getString("history", null) ?: return emptyList()
        val type = object : TypeToken<List<Vegetables>>() {}.type
        return gson.fromJson(json, type)
    }

    fun saveVegetableToHistory(vegetable: Vegetables) {
        val history = getVegetableHistory().toMutableList()

        history.removeAll { it.name == vegetable.name }

        history.add(0, vegetable)

        val json = gson.toJson(history)
        sharedPreferences.edit().putString("history", json).apply()
    }
}