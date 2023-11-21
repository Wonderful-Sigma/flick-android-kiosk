package com.staker4wapper.flick_kiosk.data.api

import com.staker4wapper.flick_kiosk.data.dto.QrDecodingResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface QRCodeApi {
    @GET("/api/search/qr")
    suspend fun decodingJwt(
        @Header("jwt") jwt: String
    ): QrDecodingResponse

}