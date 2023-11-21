package com.staker4wapper.flick_kiosk.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.staker4wapper.flick_kiosk.R

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