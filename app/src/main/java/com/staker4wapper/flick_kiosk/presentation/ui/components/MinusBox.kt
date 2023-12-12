package com.staker4wapper.flick_kiosk.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.staker4wapper.flick_kiosk.R
import com.staker4wapper.flick_kiosk.presentation.ui.theme.BasicColor

@Composable
fun MinusBox(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(BasicColor.White, RoundedCornerShape(30.dp))
            .clickable(onClick = onClick)
    ) {
        Icon(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 14.dp),
            painter = painterResource(id = R.drawable.ic_minus),
            contentDescription = ""
        )
    }
}