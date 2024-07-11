package com.staker4wapper.flick_kiosk.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.staker4wapper.flick_kiosk.data.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_data").build()

    @Provides
    @Singleton
    fun provideAppDao(database: AppDatabase) = database.productDao()

}