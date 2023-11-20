package com.staker4wapper.flick_kiosk.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.staker4wapper.flick_kiosk.R
import com.staker4wapper.flick_kiosk.Screen.Product
import com.staker4wapper.flick_kiosk.ui.theme.Flick_KioskTheme
import com.staker4wapper.flick_kiosk.ui.theme.Gray
import com.staker4wapper.flick_kiosk.ui.theme.SubTitleLarge
import com.staker4wapper.flick_kiosk.ui.theme.TitleLarge
import com.staker4wapper.flick_kiosk.ui.theme.TitleMedium

@Composable
fun ProductBox(image: Int? = null, price: Int, name: String) {
    Row(
        modifier = Modifier
            .padding(horizontal = 13.dp)
            .height(140.dp)
            .fillMaxWidth()
            .background(Color.White)
            .clickable { /*TODO*/ },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(start = 32.dp)
                .size(90.dp)
                .background(Gray.gray100),
        )
        Column(
            modifier = Modifier.padding(start = 30.dp)
        ) {
            TitleMedium(text = "${price}코인", color = Gray.gray800)
            SubTitleLarge(
                modifier = Modifier.padding(top = 4.dp),
                text = name,
                color = Gray.gray700
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            modifier = Modifier.padding(end = 32.dp),
            onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right_mini),
                contentDescription = "icArrowRightMini"
            )
        }
    }
}

@Preview
@Composable
fun GreetingPreview() {
    ProductBox(price = 380, name = "아주 맛있는 코코팜")
}