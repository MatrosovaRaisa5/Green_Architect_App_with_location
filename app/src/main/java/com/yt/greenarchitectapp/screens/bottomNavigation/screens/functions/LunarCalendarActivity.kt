package com.yt.greenarchitectapp.screens.bottomNavigation.screens.functions

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.window.Dialog
import com.yt.greenarchitectapp.R
import java.text.SimpleDateFormat
import java.util.*

class LunarCalendarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }

    @Composable
    fun HomeScreen() {
        var showPurposeDialog by remember { mutableStateOf(false) }
        var selectedDate by remember { mutableStateOf(Calendar.getInstance().time) }
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

        // Mock data
        val moonInfo = "Луна\nВосход: 14:05\nЗаход: 01:56\nФаза: 57-66%\nЛуна растущая\n2 четверть\nВ знаке зодиака Весы"
        val sunInfo = "Солнце\nВосход: 04:55\nЗаход: 21:47\nВспышек не предвидется"
        val favorableActions =
            "Благоприятно: Рыхление, окучивание, перекопка - работа возле корневой системы \nНеблагоприятно: Посадка плодово-ягодных, посадка плодовых деревьев"

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.lunkalfon),
                contentDescription = "Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(modifier = Modifier.fillMaxSize()) {
                // Header Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { finish() }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Для чего нужен календарь",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, fontSize = 14.sp),
                        color = Color(0xFF437039),
                        modifier = Modifier.clickable { showPurposeDialog = true }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painter = painterResource(id = R.drawable.kalendar),
                        contentDescription = "Calendar",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                val calendar = Calendar.getInstance()
                                DatePickerDialog(
                                    this@LunarCalendarActivity,
                                    { _, year, month, dayOfMonth ->
                                        calendar.set(year, month, dayOfMonth)
                                        selectedDate = calendar.time
                                    },
                                    calendar.get(Calendar.YEAR),
                                    calendar.get(Calendar.MONTH),
                                    calendar.get(Calendar.DAY_OF_MONTH)
                                ).show()
                            }
                    )
                }
                // Selected Date
                Text(
                    text = "${dateFormat.format(selectedDate)}",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Moon Info
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .align(Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.moon),
                        contentDescription = "Moon",
                        modifier = Modifier
                            .weight(0.5f)
                            .aspectRatio(1f)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(
                        modifier = Modifier
                            .weight(0.5f)
                            .border(3.dp, Color.Black, RoundedCornerShape(5.dp))
                            .padding(12.dp)
                    ) {
                        Text(text = moonInfo, style = MaterialTheme.typography.bodyLarge)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Sun Info
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .align(Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.sun),
                        contentDescription = "Sun",
                        modifier = Modifier
                            .weight(0.5f)
                            .aspectRatio(1f)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(
                        modifier = Modifier
                            .weight(0.5f)
                            .border(3.dp, Color.Black, RoundedCornerShape(5.dp))
                            .padding(12.dp)
                    ) {
                        Text(text = sunInfo, style = MaterialTheme.typography.bodyLarge)
                    }
                }

                Spacer(modifier = Modifier.height(13.dp))

                // Favorable Actions
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .border(3.dp, Color.Black, RoundedCornerShape(5.dp))
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = favorableActions,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Purpose Dialog
            if (showPurposeDialog) {Dialog(onDismissRequest = { showPurposeDialog = false }) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Календарь предупреждает о неблагоприятных для работы в огороде и в саду днях",
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { showPurposeDialog = false },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF437039))
                        ) {
                            Text("ОК")
                        }
                    }
                }
            }
            }
        }
    }
}