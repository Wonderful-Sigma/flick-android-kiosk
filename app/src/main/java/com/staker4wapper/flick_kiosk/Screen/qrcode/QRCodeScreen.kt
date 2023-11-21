package com.staker4wapper.flick_kiosk.Screen.qrcode

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.staker4wapper.flick_kiosk.R
import com.staker4wapper.flick_kiosk.Screen.home.Product
import com.staker4wapper.flick_kiosk.ui.components.BackArrowIconButton
import com.staker4wapper.flick_kiosk.ui.components.ProductBox
import com.staker4wapper.flick_kiosk.ui.theme.Gray
import com.staker4wapper.flick_kiosk.ui.theme.SubTitleLarge
import com.staker4wapper.flick_kiosk.ui.theme.SubTitleMedium
import com.staker4wapper.flick_kiosk.ui.theme.TitleLarge

@Composable
fun QRCodeScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.background(Color.White)
    ) {
        BackArrowIconButton(navController = navController)
        TitleLarge(
            modifier = Modifier.padding(start = 45.dp),
            text = "QR코드로 결제해주세요",
            color = Gray.gray900
        )
        SubTitleMedium(
            modifier = Modifier.padding(start = 45.dp, top = 12.dp),
            text = "결제 시 학생회 계좌로 돈이 자동으로 들어가져요",
            color = Gray.gray200
        )
        Box(
            modifier = Modifier
                .padding(horizontal = 45.dp)
                .fillMaxWidth()
                .height(600.dp)
                .padding(top = 40.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Gray.gray700)
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 45.dp)
                .padding(top = 20.dp)
        ) {
            Column {
                SubTitleLarge(text = "아주 맛있는 코코팜 (수정)", color = Gray.gray700)
                TitleLarge(modifier = Modifier.padding(top = 6.dp), text = "308코인 (수정)", color = Gray.gray700)
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.clickable { /*TODO*/ }
            ) {
                SubTitleMedium(text = "다시 스캔하기", color = Gray.gray700)
                Icon(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(30.dp),
                    painter = painterResource(id = R.drawable.ic_reload),
                    contentDescription = "icReload"
                )
            }
        }
    }
}
