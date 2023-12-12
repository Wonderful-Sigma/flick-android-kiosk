package com.staker4wapper.flick_kiosk.presentation.navigation

sealed class Screen(val route : String) {
    object Home: Screen("home_screen")
    object QRCode: Screen("qr_code_screen/{price}/{name}")
    object Load: Screen("load_screen/{price}/{user_id}")
    object Success: Screen("success_screen/{price}")
    object Failed: Screen("failed_screen")
}