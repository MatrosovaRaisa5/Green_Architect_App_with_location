package com.yt.greenarchitectapp.screens.activities

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.yt.greenarchitectapp.BaseActivity
import com.yt.greenarchitectapp.commonui.*
import com.yt.greenarchitectapp.model.Vegetables
import com.yt.greenarchitectapp.ui.theme.orange
import com.yt.greenarchitectapp.ui.theme.textGray
import com.yt.greenarchitectapp.R


class DetailActivity : BaseActivity() {

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    override fun Content() {
        Surface {
            Box(modifier = Modifier.fillMaxSize()){
                Image(painter = painterResource(id=R.drawable.detailfon),
                    contentDescription = "Background Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize())
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val data2: Vegetables= intent.extras?.getParcelable("data2")!!
                item {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, start = 30.dp, end = 30.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        CommonIconButton(icon = R.drawable.back) {
                            finish()
                        }
                        CommonIconButton(icon = R.drawable.heart)
                    }

                }

                item {
                    val pager = rememberPagerState(0)
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {


                        HorizontalPager(count = data2.listOFImages.size, state = pager) { index ->
                            ImagePagerUi(data2.listOFImages[index])
                        }
                        Spacer(modifier = Modifier.height(15.dp))

                        HorizontalPagerIndicator(pagerState = pager,
                            activeColor = orange,
                            inactiveColor = orange.copy(alpha = 0.5f)
                        )

                        Spacer(modifier = Modifier.height(0.dp))

                        Text28_600(
                            text = data2.name,
                            color = Color.Black,

                        )
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp, horizontal = 30.dp)
                    ) {
                        Text17_600(text = "Информация о растении", color = Color.Black)
                        Text15_400(
                            text = data2.info,
                            color = textGray,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 30.dp)
                    ) {
                        Text17_600(text = "Где найти?", color = Color.Black)
                        Text15_400(
                            text = data2.returnPlaces,
                            color = textGray,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(15.dp))
                    CommonButton(
                        text = "Добавить в бронь",
                        backgroundColor = orange,
                        foregroundColor = Color.White
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
        }
    }


}

@Composable
fun ImagePagerUi(
    image: Int
) {
    Image(
        painter = painterResource(id = image), contentDescription = "",
        modifier = Modifier
            .size(240.dp)
    )
}