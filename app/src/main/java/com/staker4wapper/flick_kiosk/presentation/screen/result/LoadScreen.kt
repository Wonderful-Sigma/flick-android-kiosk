package com.staker4wapper.flick_kiosk.presentation.screen.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.staker4wapper.flick_kiosk.data.dto.RemitRequest
import com.staker4wapper.flick_kiosk.presentation.navigation.Screen
import com.staker4wapper.flick_kiosk.presentation.screen.qrcode.QRViewModel
import com.staker4wapper.flick_kiosk.presentation.ui.theme.BasicColor
import com.staker4wapper.flick_kiosk.presentation.ui.theme.FlickIcon
import com.staker4wapper.flick_kiosk.presentation.ui.theme.Gray
import com.staker4wapper.flick_kiosk.presentation.ui.theme.StateColor
import com.staker4wapper.flick_kiosk.presentation.ui.theme.TitleLarge
import kotlinx.coroutines.delay

@Composable
fun LoadScreen(
    navController: NavController,
    productPrice: String,
    qrViewModel: QRViewModel = hiltViewModel(),
) {

    val sendUserAccount = qrViewModel.sendUserInfo.value!!
    qrViewModel.remit(
        RemitRequest(sendUserAccount.id.toInt(), productPrice.toLong(), 185)
    )

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
            text = "${productPrice}코인", color = Gray.gray800,
        )
        TitleLarge(text = "송금하는 중", color = Gray.gray800)
        Spacer(modifier = Modifier.weight(1f))
        FlickIcon()
    }

    LaunchedEffect(true) {
//        delay(1000)
//        navController.navigate(
//            Screen.Success.route
//                .replace(
//                    oldValue = "{price}",
//                    newValue = productPrice
//                )
//        )
        qrViewModel.remitState.collect {
            if (it.isSuccess) {
                delay(1000)
                navController.navigate(
                    Screen.Success.route
                        .replace(
                            oldValue = "{price}",
                            newValue = productPrice
                        )
                )
            }
            if (it.error.isNotEmpty()) {
                delay(1000)
                navController.navigate(Screen.Failed.route)
            }
        }
    }
}