package com.yt.greenarchitectapp.screens.bottomNavigation

import com.yt.greenarchitectapp.R


sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val icon_focused: Int
) {


    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = R.drawable.home_tab,
        icon_focused = R.drawable.home_tab
    )

    object Profile: BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = R.drawable.profile_tab,
        icon_focused = R.drawable.profile_tab
    )

    object Cat: BottomBarScreen(
        route = "Cat",
        title = "Catalog",
        icon = R.drawable.catalog,
        icon_focused = R.drawable.catalog
    )

    object Fav: BottomBarScreen(
        route = "Fav",
        title = "Favourite",
        icon = R.drawable.fav_tab,
        icon_focused = R.drawable.fav_tab
    )

    object History: BottomBarScreen(
        route = "history",
        title = "History",
        icon = R.drawable.history_tab,
        icon_focused = R.drawable.history_tab
    )

}