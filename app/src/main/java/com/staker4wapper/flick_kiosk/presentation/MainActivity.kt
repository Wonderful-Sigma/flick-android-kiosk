package com.staker4wapper.flick_kiosk.presentation

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.staker4wapper.flick_kiosk.presentation.navigation.NavGraph
import com.staker4wapper.flick_kiosk.presentation.ui.theme.Flick_KioskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Flick_KioskTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}

fun Context.findActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}
