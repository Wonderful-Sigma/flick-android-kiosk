package com.staker4wapper.flick_kiosk.presentation.utils

import android.net.Uri
import java.text.NumberFormat
import java.util.Locale

object Patten {
    fun Int.toCommaString(): String {
        val formatter = NumberFormat.getInstance(Locale.US)
        return formatter.format(this)
    }

}