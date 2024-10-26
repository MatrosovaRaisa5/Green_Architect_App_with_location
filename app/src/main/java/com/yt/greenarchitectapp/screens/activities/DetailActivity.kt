package com.yt.greenarchitectapp.screens.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.yt.greenarchitectapp.BaseActivity
import com.yt.greenarchitectapp.commonui.*
import com.yt.greenarchitectapp.model.Vegetables
import com.yt.greenarchitectapp.ui.theme.orange
import com.yt.greenarchitectapp.R
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import com.yt.greenarchitectapp.model.Nursery
import org.osmdroid.views.overlay.Marker


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
            val context = LocalContext.current
            val savedCity = getCityFromPreferences(context)
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
                        ExpandableText(
                            text = data2.info
                        )
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 30.dp)
                    ) {
                        Text17_600(text = "Агрофирмы (сайты)", color = Color.Black)
                        getNurseriesByCity(savedCity).forEach { nursery ->
                            Text15_400(
                                text = nursery.name,
                                color = Color.Blue,
                                modifier = Modifier
                                    .padding(top = 5.dp)
                                    .clickable {
                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(nursery.url))
                                        startActivity(intent)
                                    }
                            )
                        }
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp)
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text17_600(text = "Питомники поблизости", color = Color.Black)
                        MapViewComposable()
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                }


            }
        }
    }


}

fun getNurseriesByCity(city: String?): List<Nursery> {
    return when (city) {
        "Москва" -> listOf(
            Nursery(name = "«Viktoria Village", url = "https://viktoriavillage.ru/"),
            Nursery(name = "АгроСАД", url = "https://www.agrogarden.ru/"),
            Nursery(name = "Питомник Вашутино", url = "http://fittonia.ru/")
        )
        "Санкт-Петербург" -> listOf(
            Nursery(name = "Диво-Сад", url = "https://divosad-spb.ru/"),
            Nursery(name = "Питомник КЕДР", url = "http://kedrpitomnik.ru/"),
            Nursery(name = "Зелёная Фазенда", url = "http://www.kedr812.ru/")
        )
        "Новосибирск" -> listOf(
            Nursery(name = "Сибирская усадьба", url = "https://sibir-usadba.ru/"),
            Nursery(name = "Калина Красная", url = "http://kalinakrasnay.ru/"),
            Nursery(name = "Питомник Флоренс", url = "http://pitomnikflorens.ru/")
        )
        "Омск" -> listOf(
            Nursery(name = "Живой сад", url = "http://xn--80adhfkh8aza.xn--p1ai/"),
            Nursery(name = "Сибирский питомник", url = "http://sibpitomnik.ru/"),
            Nursery(name = "Сад на Оми", url = "https://www.sadnaomi.ru/")
        )
        "Казань" -> listOf(
            Nursery(name = "Питомник Клевер", url = "http://sadkzn.ru/"),
            Nursery(name = "Питомник Тюлячи", url = "http://kizilniki.ru/"),
            Nursery(name = "Биосфера", url = "http://biosfera-kazan.ru/")
        )
        "Уфа" -> listOf(
            Nursery(name = "Горзеленхоз г.Уфы", url = "http://www.ufagreen.ru/"),
            Nursery(name = "Новый сад", url = "http://isaplings.ru/"),
            Nursery(name = "Питомник Гилёвых", url = "https://xn--b1aboabkjdjij1byc1e5d.xn--p1ai/")
        )
        "Нижний Новгород" -> listOf(
            Nursery(name = "GardenFlora", url = "https://gardenflora-nn.ru/"),
            Nursery(name = "АгроФитЭк", url = "https://agrofitec.ru/"),
            Nursery(name = "Питомник школьный сад", url = "http://pitomniknn.ru/")
        )
        "Норильск" -> listOf(
            Nursery(name = "АгроСемФонд", url = "https://agrosemfond.ru"),
            Nursery(name = "Гавриш", url = "https://gavrishseeds.ru"),
            Nursery(name = "Семена.ру", url = "https://semena.ru")
        )
        "Пермь" -> listOf(
            Nursery(name = "Пермский сад", url = "https://prmsad.ru/"),
            Nursery(name = "Зелёный горизонт", url = "http://zeleniy-gorizont.ru/"),
            Nursery(name = "Ильинский питомник", url = "http://www.xn--59-6kcas9i.xn--p1ai/")
        )
        "Краснодар" -> listOf(
            Nursery(name = "Питомник Победитель", url = "https://www.pitomnic-pobeditel.ru/"),
            Nursery(name = "Садовый центр", url = "http://sadovii.ru/"),
            Nursery(name = "Клуб садоводов профессионалов", url = "https://klubsadprof.ru/")
        )
        "Владивосток" -> listOf(
            Nursery(name = "Флорегина", url = "https://floregina.ru/"),
            Nursery(name = "Приморские саженцы", url = "http://xn--b1apmabv3b.xn--p1ai/"),
            Nursery(name = "Семейный сад", url = "http://xn----7sbpnamkcjiji0af.xn--p1ai/")
        )
        "Ростов-на-Дону" -> listOf(
            Nursery(name = "Питомник Зелёный берег", url = "https://pitomnic.su/"),
            Nursery(name = "Сады Дона", url = "http://xn--80aala9bfr6f.xn--p1ai/"),
            Nursery(name = "Юнифлёр", url = "https://unifleur.ru/")
        )
        "Челябинск" -> listOf(
            Nursery(name = "Питомник Росток", url = "https://rostok-pitomnik.ru/"),
            Nursery(name = "Садовый центр Исаковский", url = "https://xn--80aeqcfdb4aye.xn--p1ai/"),
            Nursery(name = "Тепличное хозяйство Конёк-Горбунок", url = "http://xn--b1afaidikrjbdc8adl7b4a4m.xn--p1ai/")
        )
        "Екатеринбург" -> listOf(
            Nursery(name = "Садовник", url = "http://sad-24.ru/"),
            Nursery(name = "Сады России", url = "https://sady-rossii-ural.ru/"),
            Nursery(name = "Плантариум", url = "http://vserost.com/")
        )
        else -> listOf(
            Nursery(name = "Садовник", url = "http://sad-24.ru/"),
            Nursery(name = "Садовник", url = "http://sad-24.ru/"),
            Nursery(name = "Плантариум", url = "http://vserost.com/")
        )
    }
}

@Composable
fun MapViewComposable() {
    val nurseries = mapOf(
        "Москва" to listOf(
            GeoPoint(55.773244228375574, 37.55728080249214),
            GeoPoint(55.633938150400894, 37.798706619822816),
            GeoPoint(55.932191342948514, 37.42891024767718)
        ),
        "Санкт-Петербург" to listOf(
            GeoPoint(59.79381706087419, 30.316996823629868),
            GeoPoint(59.93515538859792, 30.512004150437345),
            GeoPoint(60.086853248909016, 30.355448972577822)
        ),
        "Новосибирск" to listOf(
            GeoPoint(55.016894006030505, 82.87536844714712),
            GeoPoint(54.96842797053629, 82.90498588092586),
            GeoPoint(54.945009620174375, 82.84720760847225)
        ),
        "Омск" to listOf(
            GeoPoint(54.972125789391, 73.39055907014409),
            GeoPoint(54.962556884258674, 73.4771978171679),
            GeoPoint(55.01377487185413, 73.36455348027137)
        ),
        "Казань" to listOf(
            GeoPoint(55.80451021347444, 49.260501618480916),
            GeoPoint(55.81619075153829, 49.05234972825961),
            GeoPoint(55.80256311633008, 49.21055671278288)
        ),
        "Уфа" to listOf(
            GeoPoint(54.72417122774333, 55.968857006642956),
            GeoPoint(54.74161579833119, 55.99110836840032),
            GeoPoint(54.780249976962786, 56.04030633338678)
        ),
        "Нижний Новгород" to listOf(
            GeoPoint(56.320655999614345, 43.999113561290216),
            GeoPoint(56.24382568693887, 43.97543321299798),
            GeoPoint(56.245640570534896, 43.86723851821447)
        ),
        "Пермь" to listOf(
            GeoPoint(57.98704839587042, 56.20485623439847),
            GeoPoint(57.95976724350686, 56.26773164193303),
            GeoPoint(57.93680034568467, 56.059507889708165)
        ),
        "Краснодар" to listOf(
            GeoPoint(45.02823214885414, 38.97344062426599),
            GeoPoint(45.058273647462805, 39.01422792878554),
            GeoPoint(45.11202763988643, 39.093396676787584)
        ),
        "Владивосток" to listOf(
            GeoPoint(43.22946988380034, 131.9837644190506),
            GeoPoint(43.28520750977789, 131.99109691356503),
            GeoPoint(43.31489115180396, 131.9956797226366)
        ),
        "Ростов-на-Дону" to listOf(
            GeoPoint(47.20936193423359, 39.61578343029813),
            GeoPoint(47.21799061242886, 39.74281285092976),
            GeoPoint(47.241770509442695, 39.7623822481622)
        ),
        "Челябинск" to listOf(
            GeoPoint(55.13647574568248, 61.51826422134271),
            GeoPoint(55.17655819007749, 61.388807321075646),
            GeoPoint(55.18532557954555, 61.34300764139679)
        ),
        "Екатеринбург" to listOf(
            GeoPoint(59.9342802, 30.3350986),
            GeoPoint(59.9342802, 30.3350986),
            GeoPoint(59.95, 30.31)
        )
    )
    val context = LocalContext.current
    val savedCity = getCityFromPreferences(context)
    val cityCoordinates = mapOf(
        "Москва" to GeoPoint(55.7558, 37.6173),
        "Санкт-Петербург" to GeoPoint(59.9343, 30.3351),
        "Новосибирск" to GeoPoint(55.0084, 82.0155),
        "Екатеринбург" to GeoPoint(56.8389, 60.6057),
        "Нижний Новгород" to GeoPoint(56.3269, 44.0075),
        "Казань" to GeoPoint(55.8304, 49.0661),
        "Челябинск" to GeoPoint(55.1644, 61.4368),
        "Омск" to GeoPoint(54.9920, 73.3686),
        "Ростов-на-Дону" to GeoPoint(47.2225, 39.7182),
        "Уфа" to GeoPoint(54.7388, 55.9721),
        "Пермь" to GeoPoint(58.0104, 56.2500),
        "Краснодар" to GeoPoint(45.0355, 38.9753),
        "Владивосток" to GeoPoint(43.1156, 131.8855),
        "Норильск" to GeoPoint(69.3558, 88.1893)
    )
    val cityLocation = cityCoordinates[savedCity] ?: GeoPoint(55.7558, 37.6173)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .border(BorderStroke(2.dp, Color.DarkGray))
            .clip(RoundedCornerShape(8.dp))
    ) {
        AndroidView(factory = {
            MapView(context).apply {
                Configuration.getInstance().load(context, context.getSharedPreferences("prefs", 0))
                val mapController = this.controller
                mapController.setZoom(14.0)
                controller.setCenter(cityLocation)
                setMultiTouchControls(true)

                nurseries[savedCity]?.forEachIndexed { index, geoPoint ->
                    val marker = Marker(this)
                    marker.position = geoPoint
                    marker.title = "Питомник ${index + 1}"
                    marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                    val iconRes = when (index % 2) {
                        0 -> R.drawable.redmarker
                        else -> R.drawable.graymarker
                    }
                    marker.icon = context.resources.getDrawable(iconRes, context.theme)

                    marker.setOnMarkerClickListener { _, _ ->
                        Log.d("123", "testMarkerClick")
                        // Здесь переход на следующий экран
                        true
                    }
                    overlays.add(marker)
                }

                invalidate()
            }
        }, modifier = Modifier.fillMaxSize())
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

@Composable
fun ExpandableText(
    text: String,
    maxLines: Int = 3
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = text,
            color = Color.Gray,
            maxLines = if (isExpanded) Int.MAX_VALUE else maxLines,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 5.dp)
        )

        Text(
            text = if (isExpanded) "Скрыть" else "Подробнее",
            color = Color.Blue,
            modifier = Modifier
                .clickable { isExpanded = !isExpanded }
                .padding(top = 8.dp)
        )
    }
}

fun getCityFromPreferences(context: Context): String? {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString("city_name", null)
}