package com.staker4wapper.flick_kiosk.presentation.ui.components


import com.staker4wapper.flick_kiosk.presentation.utils.Patten.toCommaString
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.staker4wapper.flick_kiosk.presentation.ui.theme.BasicColor
import com.staker4wapper.flick_kiosk.presentation.ui.theme.Gray
import com.staker4wapper.flick_kiosk.presentation.ui.theme.Gray.gray400
import com.staker4wapper.flick_kiosk.presentation.ui.theme.SubTitleLarge
import com.staker4wapper.flick_kiosk.presentation.ui.theme.TitleMedium

@Composable
fun ProductBox(
    image: ByteArray?,
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
        if (image != null) {
            Image(
                modifier = Modifier
                    .size(200.dp)
                    .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(Gray.gray50),
                bitmap = byteArrayToBitmap(image).asImageBitmap(),
                contentDescription = "productImage",
                contentScale = ContentScale.Crop
            )
        } else {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(Gray.gray50),
                contentAlignment = Alignment.Center
            ) {
                Text(text = name, textAlign = TextAlign.Center, fontSize = 20.sp, color = gray400)
            }
        }

        TitleMedium(
            modifier = Modifier.padding(start = 12.dp, top = 16.dp),
            text = name,
            color = Gray.gray800
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubTitleLarge(
                modifier = Modifier.padding(start = 12.dp, bottom = 12.dp, top = 6.dp),
                text = "${price.toCommaString()}코인",
                color = Gray.gray700
            )
            Spacer(modifier = Modifier.weight(1f))
//            CountIcon()
//            SubTitleSmall(
//                modifier = Modifier.padding(start = 6.dp, end = 12.dp),
//                text = "${count}개", color = Gray.gray300
//            )
        }
    }
}

fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
    val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    return bitmap
}

//@Preview
//@Composable
//fun GreetingPreview() {
//    ProductBox(image = "", price = 380, name = "코코팜", count = 10) {
//
//    }
//}