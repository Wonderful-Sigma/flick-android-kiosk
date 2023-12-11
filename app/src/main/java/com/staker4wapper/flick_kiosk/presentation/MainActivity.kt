package com.staker4wapper.flick_kiosk.presentation

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.staker4wapper.flick_kiosk.presentation.screen.home.HomeViewModel
import com.staker4wapper.flick_kiosk.presentation.navigation.NavGraph
import com.staker4wapper.flick_kiosk.presentation.ui.theme.Flick_KioskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Flick_KioskTheme {
                SetStatusBarColor(color = Color.Transparent)
                val homeViewModel: HomeViewModel = hiltViewModel()
                homeViewModel.getAllProducts(walletId = 70)
                val navController = rememberNavController()
                NavGraph(navController = navController, homeViewModel)
            }
        }
    }
}

@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color)
    }
}