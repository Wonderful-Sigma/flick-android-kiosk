package com.staker4wapper.flick_kiosk.presentation.ui.components


import com.staker4wapper.flick_kiosk.presentation.utils.Patten.toCommaString
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.staker4wapper.flick_kiosk.R
import com.staker4wapper.flick_kiosk.presentation.screen.home.AdminPasswordDialog
import com.staker4wapper.flick_kiosk.presentation.screen.home.PASSWORD
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
    isAdmin: Boolean = false,
    onClick: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        CheckDeleteDialog(
            onDismiss = { showDialog = false },
            onConfirm = {
                // TODO: item 삭제하기
//                showDialog = false
            }
        )
    }

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

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TitleMedium(
                modifier = Modifier.padding(start = 12.dp, top = 16.dp),
                text = name,
                color = Gray.gray800
            )
            Spacer(modifier = Modifier.weight(1f))

            if (isAdmin) {
                MenuIcon(
                    modifier = Modifier.padding(end = 24.dp, top = 16.dp),
                    onClick = { showDialog = true }
                )
            }
        }

        SubTitleLarge(
            modifier = Modifier.padding(start = 12.dp, bottom = 12.dp, top = 6.dp),
            text = "${price.toCommaString()}코인",
            color = Gray.gray700
        )
    }
}

fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
    val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    return bitmap
}

@Composable
fun MenuIcon(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ic_menu_25),
        contentDescription = "Custom Icon",
        modifier = modifier
            .size(25.dp)
            .clickable(
                onClick = onClick,
                role = Role.Button
            )
    )
}

@Composable
fun CheckDeleteDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onConfirm()
            }) {
                Text("확인", color = Color.Black, fontSize = 16.sp)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("취소", color = Color.Gray, fontSize = 16.sp)
            }
        },
        title = {
            Text("정말 이 상품을\n삭제하시겠어요?", fontSize = 18.sp, color= Color.Black)
        },
        modifier = Modifier
            .padding(16.dp)
            .background(Color.White, RoundedCornerShape(20.dp))
    )
}