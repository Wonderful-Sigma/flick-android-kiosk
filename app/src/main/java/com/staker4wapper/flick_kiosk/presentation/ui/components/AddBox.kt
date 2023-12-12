package com.staker4wapper.flick_kiosk.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.staker4wapper.flick_kiosk.presentation.ui.theme.BasicColor
import com.staker4wapper.flick_kiosk.presentation.ui.theme.Gray
import com.staker4wapper.flick_kiosk.presentation.ui.theme.SubTitleLarge

@Composable
fun AddBox(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(start = 15.dp)
            .background(BasicColor.White, RoundedCornerShape(30.dp))
            .clickable(onClick = onClick)
    ) {
        SubTitleLarge(
            modifier = Modifier.padding(horizontal = 34.dp, vertical = 21.dp),
            text = "1개 더 추가하기", color = Gray.gray800
        )
    }
}