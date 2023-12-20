package com.staker4wapper.flick_kiosk.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.staker4wapper.flick_kiosk.presentation.ui.theme.Gray

@Composable
fun LineBox(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.height(16.dp)
            .fillMaxWidth()
            .background(Gray.gray50)
    )
}