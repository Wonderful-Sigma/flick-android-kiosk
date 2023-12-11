package com.staker4wapper.flick_kiosk.presentation.screen.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.staker4wapper.flick_kiosk.presentation.navigation.Screen
import com.staker4wapper.flick_kiosk.presentation.ui.theme.BasicColor
import com.staker4wapper.flick_kiosk.presentation.ui.theme.FlickIcon
import com.staker4wapper.flick_kiosk.presentation.ui.theme.Gray
import com.staker4wapper.flick_kiosk.presentation.ui.theme.StateColor
import com.staker4wapper.flick_kiosk.presentation.ui.theme.TitleLarge
import kotlinx.coroutines.delay

@Composable
fun SuccessScreen(
    navController: NavController,
    productPrice: String,
) {
    Column(
        modifier = Modifier.background(BasicColor.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Box(modifier = Modifier
            .size(100.dp)
            .background(StateColor.Success, CircleShape)
        )
        TitleLarge(
            modifier = Modifier.padding(top = 30.dp),
            text = "${productPrice}코인이", color = Gray.gray800,
        )
        TitleLarge(text = "결제되었어요", color = Gray.gray800)
        Spacer(modifier = Modifier.weight(1f))
        FlickIcon()
    }

    LaunchedEffect(true) {
        delay(2500)
        navController.navigate(Screen.Home.route) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }
}