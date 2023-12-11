package com.staker4wapper.flick_kiosk.presentation.Screen.qrcode

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamsoft.dbr.BarcodeReader
import com.staker4wapper.flick_kiosk.R
import com.staker4wapper.flick_kiosk.presentation.ui.components.BackArrowIconButtonForQRView
import com.staker4wapper.flick_kiosk.presentation.ui.theme.Gray
import com.staker4wapper.flick_kiosk.presentation.ui.theme.SubTitleLarge
import com.staker4wapper.flick_kiosk.presentation.ui.theme.SubTitleMedium
import com.staker4wapper.flick_kiosk.presentation.ui.theme.TitleLarge
import com.staker4wapper.flick_kiosk.presentation.ui.theme.TitleMedium

@Composable
fun QRCodeScreen(
    navController: NavController,
    productPrice: String,
    productName: String,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        BackArrowIconButtonForQRView {
            /*TODO*/
            navController.popBackStack()
        }
        TitleLarge(
            modifier = Modifier.padding(start = 45.dp),
            text = "QR코드로 결제해주세요",
            color = Gray.gray900
        )
        SubTitleMedium(
            modifier = Modifier.padding(start = 45.dp, top = 12.dp),
            text = "결제 시 학생회 계좌로 돈이 자동으로 들어가져요",
            color = Gray.gray300
        )
        CameraView(productPrice.toLong(), navController = navController)
        Row(
            modifier = Modifier
                .padding(horizontal = 45.dp)
                .padding(top = 20.dp)
        ) {
            Column {
                SubTitleLarge(text = productName, color = Gray.gray700)
                TitleLarge(
                    modifier = Modifier.padding(top = 6.dp),
                    text = productPrice + "코인",
                    color = Gray.gray700
                )
            }
            Spacer(modifier = Modifier.weight(1f))
//            Row(
//                modifier = Modifier.clickable {
//                    Toast.makeText(context, "지금은 없는 기능이에요..", Toast.LENGTH_SHORT).show()
//                }
//            ) {
//                SubTitleMedium(text = "다시 스캔하기", color = Gray.gray700)
//                Icon(
//                    modifier = Modifier
//                        .padding(start = 8.dp)
//                        .size(30.dp),
//                    painter = painterResource(id = R.drawable.ic_reload),
//                    contentDescription = "icReload"
//                )
//            }
        }
    }
}