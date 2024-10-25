package com.yt.greenarchitectapp.model

import com.yt.greenarchitectapp.R

data class Drawer(
    val icon:Int,
    val name:String
)

val drawerContent = listOf(
    Drawer(
        R.drawable.profile_icon,
        "АгроСовет"
    ),
    Drawer(
        R.drawable.offer_icon,
        "Скидки и купоны"
    ),
    Drawer(
        R.drawable.privacy_icon,
        "Правила"
    ),
    Drawer(
        R.drawable.exit_icon,
        "Выход"
    ),
)