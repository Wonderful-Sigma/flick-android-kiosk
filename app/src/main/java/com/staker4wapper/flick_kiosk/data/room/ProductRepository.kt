package com.staker4wapper.flick_kiosk.data.room

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productDao: ProductDao
){
    fun getProduct(): Flow<List<Product>> = productDao.products()

    suspend fun insert(product: Product) {}
}