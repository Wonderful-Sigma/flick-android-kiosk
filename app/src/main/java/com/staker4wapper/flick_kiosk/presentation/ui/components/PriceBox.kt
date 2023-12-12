package com.staker4wapper.flick_kiosk.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.staker4wapper.flick_kiosk.presentation.ui.theme.BasicColor
import com.staker4wapper.flick_kiosk.presentation.ui.theme.Gray
import com.staker4wapper.flick_kiosk.presentation.ui.theme.TitleLarge
import com.staker4wapper.flick_kiosk.presentation.ui.theme.TitleSmall

@Composable
fun PriceBox(price: Int) {
    Row(
        modifier = Modifier
            .height(152.dp)
            .fillMaxWidth()
            .padding(horizontal = 137.dp)
            .padding(top = 10.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(BasicColor.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TitleSmall(
            modifier = Modifier.padding(start = 34.dp),
            text = "결제 금액", color = Gray.gray800
        )
        Spacer(modifier = Modifier.weight(1f))
        TitleLarge(
            modifier = Modifier.padding(end = 34.dp),
            text = "${price}코인", color = Gray.gray800
        )
    }
}