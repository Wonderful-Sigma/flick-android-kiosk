package com.staker4wapper.flick_kiosk.presentation.ui.components

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.staker4wapper.flick_kiosk.R
import com.staker4wapper.flick_kiosk.presentation.ui.theme.BasicColor
import com.staker4wapper.flick_kiosk.presentation.ui.theme.Gray
import com.staker4wapper.flick_kiosk.presentation.ui.theme.SubTitleLarge
import com.staker4wapper.flick_kiosk.presentation.ui.theme.TitleMedium

@Composable
fun ProductBox(
    image: String,
    price: Int,
    name: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(BasicColor.White)
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = onClick),
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                .size(200.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(Gray.gray50),
            model = image,
            contentDescription = "productImage"
        )
        TitleMedium(
            modifier = Modifier.padding(start = 12.dp, top = 16.dp),
            text = name,
            color = Gray.gray800
        )
        SubTitleLarge(
            modifier = Modifier.padding(start = 12.dp, bottom = 12.dp, top = 6.dp),
            text = "${price}코인",
            color = Gray.gray700
        )
    }
}

@Preview
@Composable
fun GreetingPreview() {
    ProductBox(image = "", price = 380, name = "코코팜") {

    }
}