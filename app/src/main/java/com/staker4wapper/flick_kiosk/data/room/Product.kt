package com.staker4wapper.flick_kiosk.data.room

import android.graphics.Bitmap
import androidx.room.*

@Entity
data class Product(
    @PrimaryKey val uid: String,
    @ColumnInfo val name: String,
    @ColumnInfo val price: Int,
    @ColumnInfo var image: ByteArray?
)
