package com.staker4wapper.flick_kiosk.data.api

import com.staker4wapper.flick_kiosk.data.dto.ProductResponse
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ProductApi {

    @PATCH("/api/goods/searchAll/{walletId}")
    suspend fun getAllProducts(
        @Path("walletId") walletId: Int
    ): List<ProductResponse>

}