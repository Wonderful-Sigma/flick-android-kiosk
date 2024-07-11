package com.staker4wapper.flick_kiosk.data.repository

import com.staker4wapper.flick_kiosk.data.api.ProductApi
import com.staker4wapper.flick_kiosk.data.dto.ProductResponse
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productApi: ProductApi
) {

    suspend fun getAllProduct(walletId: Int): List<ProductResponse> =
        productApi.getAllProducts(walletId)
}