package com.staker4wapper.flick_kiosk.presentation.ui.theme

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.staker4wapper.flick_kiosk.R

@Composable
fun FlickIcon() {
    Row {
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            modifier = Modifier.padding(bottom = 40.dp),
            painter = painterResource(id = R.drawable.ic_flick),
            contentDescription = "",
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun BackArrowIconButton(navController: NavController) {
    IconButton(
        modifier = Modifier.padding(20.dp),
        onClick = {
            navController.popBackStack()
        }) {
        Icon(
            modifier = Modifier.size(40.dp),
            painter = painterResource(id = R.drawable.ic_arrow_left_big),
            contentDescription = "icArrowLeftBig"
        )
    }
}