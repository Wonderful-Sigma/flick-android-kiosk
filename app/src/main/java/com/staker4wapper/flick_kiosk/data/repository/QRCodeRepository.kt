package com.staker4wapper.flick_kiosk.data.repository

import com.staker4wapper.flick_kiosk.data.api.QRCodeApi
import com.staker4wapper.flick_kiosk.data.dto.QrDecodingResponse
import com.staker4wapper.flick_kiosk.data.dto.RemitRequest
import com.staker4wapper.flick_kiosk.data.dto.RemitResponse
import javax.inject.Inject

class QRCodeRepository @Inject constructor(
    private val qrCodeApi: QRCodeApi
) {
    suspend fun decodingQrCode(jwt: String): QrDecodingResponse =
        qrCodeApi.decodingQrCode(jwt)

    suspend fun remit(remitRequest: RemitRequest): RemitResponse =
        qrCodeApi.remit(remitRequest)

}