package com.staker4wapper.flick_kiosk.data.dto

data class PaymentRequest(
    val goodsIds: List<Long>,
    val remittanceAccount: Long,
    val goodsCounts: List<Short>
)

