package com.yt.greenarchitectapp.screens.bottomNavigation.screens

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.yt.greenarchitectapp.commonui.Text34_700
import com.yt.greenarchitectapp.screens.activities.CartActivity
import com.yt.greenarchitectapp.model.Vegetables
import com.yt.greenarchitectapp.screens.activities.DetailActivity
import com.yt.greenarchitectapp.utils.launchActivity

@Composable
fun WishListTab(
    navController: NavHostController
) {
    val context = LocalContext.current
    var favoriteItems by remember { mutableStateOf(FavoriteManager.getFavoriteVegetables(context)) }
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                favoriteItems = FavoriteManager.getFavoriteVegetables(context)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
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
                    text = "Забронированное",
                    color = Color.Black,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }

            items(favoriteItems) { vegetable ->
                EachRowCat(
                    vegetables = vegetable
                ) { selectedVegetable ->
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra("data2", selectedVegetable)
                    }
                    context.startActivity(intent)
                }
            }


            if (favoriteItems.isEmpty()) {
                item {
                    Text34_700(
                        text = "Список избранного пуст",
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                    )
                }
            }
        }
    }
}

object FavoriteManager {
    private const val PREF_NAME = "vegetable_favorites"
    private const val KEY_FAVORITES = "favorites"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    private fun saveFavorites(context: Context, favoriteList: List<Vegetables>) {
        val gson = Gson()
        val json = gson.toJson(favoriteList)
        getSharedPreferences(context).edit().putString(KEY_FAVORITES, json).apply()
    }

    fun addToFavorites(context: Context, vegetable: Vegetables) {
        val favoriteList = getFavoriteVegetables(context).toMutableList()
        if (!favoriteList.contains(vegetable)) {
            favoriteList.add(vegetable)
            saveFavorites(context, favoriteList)
        }
    }

    fun removeFromFavorites(context: Context, vegetable: Vegetables) {
        val favoriteList = getFavoriteVegetables(context).toMutableList()
        favoriteList.remove(vegetable)
        saveFavorites(context, favoriteList)
    }

    fun getFavoriteVegetables(context: Context): List<Vegetables> {
        val json = getSharedPreferences(context).getString(KEY_FAVORITES, null) ?: return emptyList()
        val gson = Gson()
        val type = object : TypeToken<List<Vegetables>>() {}.type
        return gson.fromJson(json, type)
    }

    fun isFavorite(context: Context, vegetable: Vegetables): Boolean {
        val favoriteList = getFavoriteVegetables(context)
        return favoriteList.contains(vegetable)
    }
}
