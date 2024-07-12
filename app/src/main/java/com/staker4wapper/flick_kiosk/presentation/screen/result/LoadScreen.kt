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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.staker4wapper.flick_kiosk.R
import com.staker4wapper.flick_kiosk.data.dto.PaymentRequest
import com.staker4wapper.flick_kiosk.data.dto.RemitRequest
import com.staker4wapper.flick_kiosk.presentation.navigation.Screen
import com.staker4wapper.flick_kiosk.presentation.screen.qrcode.QRViewModel
import com.staker4wapper.flick_kiosk.presentation.screen.qrcode.state.RemitState
import com.staker4wapper.flick_kiosk.presentation.ui.theme.BasicColor
import com.staker4wapper.flick_kiosk.presentation.ui.theme.FlickIcon
import com.staker4wapper.flick_kiosk.presentation.ui.theme.Gray
import com.staker4wapper.flick_kiosk.presentation.ui.theme.StateColor
import com.staker4wapper.flick_kiosk.presentation.ui.theme.TitleLarge
import kotlinx.coroutines.delay
import newjeans.bunnies.data.DataManager

@Composable
fun LoadScreen(
    navController: NavController,
    sendUserId: String,
    productPrice: String,
    dataManager: DataManager,
    qrViewModel: QRViewModel = hiltViewModel(),
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_load2))

    Column(
        modifier = Modifier.background(BasicColor.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        LottieAnimation(
            modifier = Modifier.size(200.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever,
            contentScale = ContentScale.FillHeight
        )
        TitleLarge(
            text = "${productPrice}코인", color = Gray.gray800,
        )
        TitleLarge(text = "결제하는 중", color = Gray.gray800)
        Spacer(modifier = Modifier.weight(1f))
        FlickIcon()
    }

    LaunchedEffect(true) {
//        qrViewModel.payment(
//            // 로투스 비스코프 2개
//            PaymentRequest(listOf(9), sendUserId.toLong(), listOf(2))
//        )
        qrViewModel.remit(
            RemitRequest(sendUserId.toInt(), productPrice.toLong(), 191), dataManager
        )
        qrViewModel.remitState.collect {
            if (it.isSuccess) {
                navController.navigate(
                    Screen.Success.route
                        .replace(
                            oldValue = "{price}",
                            newValue = productPrice
                        )
                )
            }
            if (it.error.isNotEmpty()) {
                navController.navigate(Screen.Failed.route)
            }
        }
    }
}