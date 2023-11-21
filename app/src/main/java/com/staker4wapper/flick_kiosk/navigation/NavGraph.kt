package com.staker4wapper.flick_kiosk.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.staker4wapper.flick_kiosk.Screen.HomeScreen
import com.staker4wapper.flick_kiosk.navigation.Screen

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
//            LoginScreen(navController = navController)
        }
    }
}