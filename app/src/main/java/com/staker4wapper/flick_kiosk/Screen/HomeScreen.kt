package com.staker4wapper.flick_kiosk.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.staker4wapper.flick_kiosk.ui.components.ProductBox
import com.staker4wapper.flick_kiosk.ui.theme.Flick_KioskTheme
import com.staker4wapper.flick_kiosk.ui.theme.Gray
import com.staker4wapper.flick_kiosk.ui.theme.TitleLarge

@Composable
fun HomeScreen() {

    val list = listOf(
        Product(price = 308, name = "아주 맛있는 코코팜"),
        Product(price = 308, name = "아주 맛있는 코코팜"),
        Product(price = 308, name = "아주 맛있는 코코팜"),
        Product(price = 308, name = "아주 맛있는 코코팜"),
    )

    Column(
        modifier = Modifier.background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        TitleLarge(
            modifier = Modifier.padding(start = 45.dp),
            text = "구매할 상품을 선택해주세요",
            color = Gray.gray900
        )
        Spacer(modifier = Modifier.height(40.dp))
        LazyColumn {
            items(list.size) {
                ProductBox(price = list[it].price, name = list[it].name)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Flick_KioskTheme {
        HomeScreen()
    }
}