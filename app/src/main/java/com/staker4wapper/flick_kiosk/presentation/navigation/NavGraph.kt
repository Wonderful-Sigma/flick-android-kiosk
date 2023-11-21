package com.staker4wapper.flick_kiosk.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.staker4wapper.flick_kiosk.presentation.Screen.home.HomeScreen
import com.staker4wapper.flick_kiosk.presentation.Screen.qrcode.QRCodeScreen

@Composable
fun NavGraph(
    navController : NavHostController,
){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.QRCode.route){
            QRCodeScreen(navController = navController)
        }
    }
}