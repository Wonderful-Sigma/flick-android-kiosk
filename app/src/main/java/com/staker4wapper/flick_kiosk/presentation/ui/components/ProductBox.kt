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
    Row(
        modifier = Modifier
            .padding(horizontal = 13.dp)
            .height(140.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
//        Box(
//            modifier = Modifier
//                .padding(start = 32.dp)
//                .size(90.dp)
//                .clip(RoundedCornerShape(20.dp))
//                .background(Gray.gray100),
//        )
        AsyncImage(
            modifier = Modifier
                .padding(start = 32.dp)
                .size(90.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Gray.gray50),
            model = image,
            contentDescription = "productImage"
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
                contentDescription = "icArrowRightMini",
                tint = Gray.gray200
            )
        }
    }
}

@Preview
@Composable
fun GreetingPreview() {
    ProductBox(image = "", price = 380, name = "아주 맛있는 코코팜") {

    }
}