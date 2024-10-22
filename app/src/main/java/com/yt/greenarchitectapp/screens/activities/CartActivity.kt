package com.yt.greenarchitectapp.screens.activities

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yt.greenarchitectapp.BaseActivity
import com.yt.greenarchitectapp.commonui.CommonHeader
import com.yt.greenarchitectapp.commonui.Text17_600
import com.yt.greenarchitectapp.model.Vegetables
import com.yt.greenarchitectapp.model.listOfVegetables
import com.yt.greenarchitectapp.R

class CartActivity : BaseActivity() {

    @Composable
    override fun Content() {
        Surface {
            Box(modifier = Modifier.fillMaxSize()){
                Image(painter = painterResource(id=R.drawable.fon),
                    contentDescription = "Background Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize())
            }
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
                        items(listOfVegetables) { vegetables ->
                            EachRow(vegetables = vegetables)
                        }
                    }

                }
            }
        }
    }

    @Composable
    fun EachRow(
        vegetables: Vegetables
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
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
            }
        }
    }
}
