package com.yt.greenarchitectapp.screens.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yt.greenarchitectapp.BaseActivity // Убедитесь, что этот импорт нужен, иначе удалите его.
import com.yt.greenarchitectapp.commonui.CommonHeader
import com.yt.greenarchitectapp.commonui.Text17_600
import com.yt.greenarchitectapp.model.Vegetables
import com.yt.greenarchitectapp.model.listOfVegetables

import com.yt.greenarchitectapp.R
import com.yt.greenarchitectapp.commonui.CommonButton
import com.yt.greenarchitectapp.commonui.CommonIconButton
import com.yt.greenarchitectapp.commonui.Text22_700
import com.yt.greenarchitectapp.commonui.Text34_700
import com.yt.greenarchitectapp.commonui.Text65_800
import com.yt.greenarchitectapp.commonui.Text67_800
import com.yt.greenarchitectapp.ui.theme.Purple200
import com.yt.greenarchitectapp.ui.theme.Purple500
import com.yt.greenarchitectapp.ui.theme.Purple700
import com.yt.greenarchitectapp.ui.theme.orange
import com.yt.greenarchitectapp.utils.launchActivity
import kotlinx.coroutines.launch

class GreyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content()
        }
    }

    @Composable
    fun Content() {
        Surface {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.regfon),
                    contentDescription = "Background Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                val context = LocalContext.current
                val nurseryName = intent.getStringExtra("nursery_name")

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        CommonHeader(text = "") {
                            finish()
                        }

                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            item {
                                if (nurseryName != null) {
                                    Text67_800(
                                        text = nurseryName,
                                        color = Color.Black,
                                        modifier = Modifier.padding(horizontal = 20.dp)
                                    )
                                }
                            }
                            item {
                                Text22_700(
                                    text = "К сожалению, наличие товара не подтверждено",
                                    color = Color.Black,
                                    modifier = Modifier.padding(horizontal = 20.dp)
                                )
                            }

                            item {
                                Text22_700(
                                    text = "Попробуйте позже.",
                                    color = Color.Black,
                                    modifier = Modifier.padding(horizontal = 20.dp)
                                )
                            }
                            item {
                            Spacer(modifier = Modifier.height(95.dp))}
                            item {
                                Text67_800(
                                    text = "Удачных покупок!",
                                    color = orange,
                                    modifier = Modifier.padding(horizontal = 0.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    CommonButton(
                        text = "Уведомить о наличии",
                        backgroundColor = orange,
                        foregroundColor = Color.White
                    ) {

                    }
                }

            }
        }
    }

}