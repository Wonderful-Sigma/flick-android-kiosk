package com.staker4wapper.flick_kiosk.presentation.screen.qrcode

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.staker4wapper.flick_kiosk.R
import com.staker4wapper.flick_kiosk.presentation.screen.qrcode.scanner.CameraView
import com.staker4wapper.flick_kiosk.presentation.ui.components.PriceBox
import com.staker4wapper.flick_kiosk.presentation.ui.theme.BackArrowIconButton
import com.staker4wapper.flick_kiosk.presentation.ui.theme.BasicColor
import com.staker4wapper.flick_kiosk.presentation.ui.theme.FlickIcon
import com.staker4wapper.flick_kiosk.presentation.ui.theme.Gray
import com.staker4wapper.flick_kiosk.presentation.ui.theme.SubTitleLarge
import com.staker4wapper.flick_kiosk.presentation.ui.theme.TitleLarge
import com.staker4wapper.flick_kiosk.presentation.ui.theme.TitleSmall

@Composable
fun QRCodeScreen(
    navController: NavController,
    productPrice: String,
    productName: String,
) {
    Column(
        modifier = Modifier
            .background(BasicColor.Background)
            .fillMaxSize(),
    ) {
        BackArrowIconButton(navController = navController)
        PriceBox(price = productPrice)
        CameraView(productPrice.toLong(), navController = navController)
        SubTitleLarge(
            modifier = Modifier.padding(top = 24.dp).fillMaxWidth(),
            text = "앱으로 생성한 QR코드를 인식시켜주세요", color = Gray.gray300,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1f))
        FlickIcon()
    }

    BackHandler {
        navController.popBackStack()
    }
}