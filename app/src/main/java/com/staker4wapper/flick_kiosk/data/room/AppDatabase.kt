package com.staker4wapper.flick_kiosk.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun productDao(): ProductDao
}