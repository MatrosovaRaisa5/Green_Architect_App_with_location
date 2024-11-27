package com.yt.greenarchitectapp.screens.activities

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yt.greenarchitectapp.BaseActivity
import com.yt.greenarchitectapp.commonui.CommonHeader
import com.yt.greenarchitectapp.commonui.Text17_600
import com.yt.greenarchitectapp.model.Vegetables
import android.util.Log
import com.yt.greenarchitectapp.R
import com.yt.greenarchitectapp.commonui.CommonIconButton

class CartActivity : BaseActivity() {

    @Composable
    override fun Content() {
        Surface {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.regfon),
                    contentDescription = "Background Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            val context = LocalContext.current
            val cartManager = remember {CartManager(context) }
            val reservedVegetables = remember { mutableStateOf(cartManager.loadReservedVegetables()) }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    CommonHeader(text = "Забронированное") {
                        finish()
                    }
                    LazyColumn {
                        items(reservedVegetables.value) { vegetable ->
                            EachRow(
                                vegetables = vegetable,
                                cartManager = cartManager,
                                reservedVegetables = reservedVegetables
                            ) { selectedVegetable ->
                                val intent = Intent(context, DetailActivity::class.java).apply {
                                    putExtra("data2", selectedVegetable)
                                }
                                context.startActivity(intent)
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun EachRow(
        vegetables: Vegetables,
        cartManager: CartManager,
        reservedVegetables: MutableState<List<Vegetables>>,
        onClick: (Vegetables) -> Unit
    ) {
        var showInfoPopup by remember { mutableStateOf(false) }
        var buttonCoordinates by remember { mutableStateOf(Offset.Zero) }
        val density = LocalDensity.current

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .clickable { onClick(vegetables) },
            elevation = 1.dp,
            shape = RoundedCornerShape(20.dp)
        ) {
            Box(modifier = Modifier.background(Color.White)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
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

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .onGloballyPositioned { layoutCoordinates ->
                                buttonCoordinates = layoutCoordinates.positionInRoot()
                            }
                    ) {
                        CommonIconButton(icon = R.drawable.info) {
                            showInfoPopup = !showInfoPopup
                        }
                    }

                    CommonIconButton(icon = R.drawable.cart) {
                        cartManager.removeVegetable(vegetables)
                        reservedVegetables.value = cartManager.loadReservedVegetables()
                    }
                }
            }
        }
        if (showInfoPopup) {
            Popup(
                alignment = Alignment.TopStart,
                offset = with(density) {
                    IntOffset(
                        x = buttonCoordinates.x.toInt(),
                        y = (buttonCoordinates.y - 20.dp.toPx()).toInt()
                    )
                },
                onDismissRequest = { showInfoPopup = false }
            ) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFFEFEFEF), shape = RoundedCornerShape(10.dp))
                        .padding(16.dp)
                ) {
                    Column{
                        Text(text = "Питомник: Сад на Оми", color = Color.Black)
                        Text(text = "Название: ${vegetables.name}", color = Color.Black)
                        Text(text = "Количество товара: 3", color = Color.Black)
                        Text(text = "Дата начала брони: 01.12.2024", color = Color.Black)
                        Text(text = "Дата окончания брони: 15.12.2024", color = Color.Black)
                    }
                }
            }
        }
    }
}

class CartManager(private val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("reserved_list", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun loadReservedVegetables(): List<Vegetables> {
        val json = sharedPreferences.getString("reserved_vegetables", "[]") ?: "[]"
        val type = object : TypeToken<List<Vegetables>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }

    fun addVegetable(vegetable: Vegetables) {
        if (!isReserved(vegetable)) {
            val currentList = loadReservedVegetables().toMutableList()
            currentList.add(vegetable)
            saveReservedVegetables(currentList)
        }
    }

    fun removeVegetable(vegetable: Vegetables) {
        val currentList = loadReservedVegetables().toMutableList()
        currentList.remove(vegetable)
        saveReservedVegetables(currentList)
    }

    private fun saveReservedVegetables(vegetables: List<Vegetables>) {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(vegetables)
        editor.putString("reserved_vegetables", json)
        editor.apply()
    }

    fun isReserved(vegetable: Vegetables): Boolean {
        return loadReservedVegetables().contains(vegetable)
    }
}
