package com.staker4wapper.flick_kiosk.data.api

import com.staker4wapper.flick_kiosk.data.dto.PaymentRequest
import com.staker4wapper.flick_kiosk.data.dto.PaymentResponse
import com.staker4wapper.flick_kiosk.data.dto.QrDecodingResponse
import com.staker4wapper.flick_kiosk.data.dto.RemitRequest
import com.staker4wapper.flick_kiosk.data.dto.RemitResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH

interface QRCodeApi {
    @GET("/api/QrCode/search/qr")
    suspend fun decodingQrCode(
        @Header("jwt") jwt: String
    ): QrDecodingResponse

    @PATCH("/api/wallet/management/remit")
    suspend fun remit(
        @Body remitRequest: RemitRequest
    ): RemitResponse

    @PATCH("/payment")
    suspend fun payment(
        @Body paymentRequest: PaymentRequest
    ): PaymentResponse

}