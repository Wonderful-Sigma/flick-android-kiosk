package com.staker4wapper.flick_kiosk.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun products(): Flow<List<Product>>

    @Insert
    suspend fun insert(vararg product: Product)

    @Query("DELETE from product where uid = :uid")
    suspend fun delete(uid: String)
}