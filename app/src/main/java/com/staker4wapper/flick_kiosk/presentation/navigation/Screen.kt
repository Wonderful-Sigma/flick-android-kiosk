package com.staker4wapper.flick_kiosk.presentation.navigation

sealed class Screen(val route : String) {
    object Home: Screen("home_screen")
    object QRCode: Screen("qr_code_screen/{price}/{name}")
    object Success: Screen("success_screen/{price}")
    object Failed: Screen("failed_screen")
}