package com.staker4wapper.flick_kiosk.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.staker4wapper.flick_kiosk.R

val pretendard = FontFamily(
    Font(R.font.pretendard_bold, FontWeight.Bold),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
    Font(R.font.pretendard_regular, FontWeight.Normal),
)

@Composable
fun TitleLarge(modifier: Modifier = Modifier, text: String, color: Color) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontFamily = pretendard,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    )
}
@Composable
fun TitleMedium(modifier: Modifier = Modifier, text: String, color: Color) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontFamily = pretendard,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp
    )
}
@Composable
fun SubTitleLarge(modifier: Modifier = Modifier, text: String, color: Color) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp
    )
}
@Composable
fun SubTitleMedium(modifier: Modifier = Modifier, text: String, color: Color) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    )
}