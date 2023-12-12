package com.staker4wapper.flick_kiosk.presentation.screen.qrcode

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.staker4wapper.flick_kiosk.presentation.screen.qrcode.scanner.CameraView
import com.staker4wapper.flick_kiosk.presentation.ui.components.AddBox
import com.staker4wapper.flick_kiosk.presentation.ui.components.MinusBox
import com.staker4wapper.flick_kiosk.presentation.ui.components.PriceBox
import com.staker4wapper.flick_kiosk.presentation.ui.theme.BackArrowIconButton
import com.staker4wapper.flick_kiosk.presentation.ui.theme.BasicColor
import com.staker4wapper.flick_kiosk.presentation.ui.theme.FlickIcon
import com.staker4wapper.flick_kiosk.presentation.ui.theme.Gray
import com.staker4wapper.flick_kiosk.presentation.ui.theme.SubTitleLarge

@Composable
fun QRCodeScreen(
    navController: NavController,
    productPrice: String,
    productName: String,
) {
    var lastProductPrice by remember { mutableIntStateOf(productPrice.toInt()) }
    var productAmount by remember { mutableIntStateOf(1) }

    Column(
        modifier = Modifier
            .background(BasicColor.Background)
            .fillMaxSize(),
    ) {
        BackArrowIconButton(navController = navController)
        PriceBox(price = lastProductPrice)
        CameraView(lastProductPrice, navController = navController)
        SubTitleLarge(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(),
            text = "현재 $productName ${productAmount}개 구매 중", color = Gray.gray800,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            MinusBox {
                if (productAmount > 1) {
                    productAmount -= 1
                    lastProductPrice = productPrice.toInt() * productAmount
                }
            }
            AddBox {
                productAmount += 1
                lastProductPrice = productPrice.toInt() * productAmount
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        FlickIcon()
    }

    BackHandler {
        navController.popBackStack()
    }
}