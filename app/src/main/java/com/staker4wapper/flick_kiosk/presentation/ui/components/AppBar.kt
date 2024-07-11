package com.staker4wapper.flick_kiosk.presentation.ui.components

import androidx.compose.foundation.Image
import com.staker4wapper.flick_kiosk.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.staker4wapper.flick_kiosk.presentation.ui.theme.Gray.gray100
import com.staker4wapper.flick_kiosk.presentation.ui.theme.Gray.gray400
import com.staker4wapper.flick_kiosk.presentation.ui.theme.SubTitleLarge

@Composable
//@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
fun AppBar(state: Boolean) {
    Row(
        modifier = Modifier
            .height(80.dp)
            .padding(horizontal = 45.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SubTitleLarge(text = "총 결재된 금액 : 1,000대소코인", color = gray400)
        Spacer(modifier = Modifier.weight(1F))
        Row(
            modifier = Modifier
                .background(gray100, RoundedCornerShape(8.dp))
                .height(37.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_change),
                contentDescription = "",
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = if(state) "어드민 변경" else "클라이언트 변경",
                color = Color(0xFF1A1E27),
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    }
}