package com.yt.greenarchitectapp.screens.bottomNavigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yt.greenarchitectapp.screens.bottomNavigation.screens.HistoryTab
import com.yt.greenarchitectapp.screens.bottomNavigation.screens.HomeTab
import com.yt.greenarchitectapp.screens.bottomNavigation.screens.ProfileTab
import com.yt.greenarchitectapp.screens.bottomNavigation.screens.CatalogTab
import com.yt.greenarchitectapp.screens.bottomNavigation.screens.WishListTab


@Composable
fun HomeNavigation(
    navHostController: NavHostController,
    scaffoldState: ScaffoldState
) {

    NavHost(navController = navHostController, startDestination = BottomBarScreen.Home.route ){
        composable(BottomBarScreen.Home.route){
            HomeTab(scaffoldState)
        }
        composable(BottomBarScreen.Profile.route){
            ProfileTab(navHostController)
        }

        composable(BottomBarScreen.Cat.route){
            CatalogTab(navHostController)
        }

        composable(BottomBarScreen.Fav.route){
            WishListTab()
        }

        composable(BottomBarScreen.History.route){
            HistoryTab()
        }
    }

}