package com.staker4wapper.flick_kiosk.presentation.screen.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.staker4wapper.flick_kiosk.presentation.navigation.Screen
import com.staker4wapper.flick_kiosk.presentation.ui.theme.FlickIcon
import com.staker4wapper.flick_kiosk.presentation.ui.theme.Gray
import com.staker4wapper.flick_kiosk.presentation.ui.theme.StateColor
import com.staker4wapper.flick_kiosk.presentation.ui.theme.TitleLarge

@Composable
fun FailedScreen(
    navController: NavController,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Box(modifier = Modifier.size(100.dp).background(StateColor.Failed))
        TitleLarge(
            modifier = Modifier.padding(top = 30.dp),
            text = "간식을 구매하기엔\n코인이 부족해요", color = Gray.gray800
        )
        Spacer(modifier = Modifier.weight(1f))
        FlickIcon()
    }
    Thread.sleep(2000)
    navController.navigate(Screen.Home.route)
}