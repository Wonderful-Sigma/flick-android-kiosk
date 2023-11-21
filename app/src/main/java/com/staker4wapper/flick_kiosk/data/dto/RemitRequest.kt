package com.staker4wapper.flick_kiosk.data.dto

data class RemitRequest(
    val remittanceAccount: Int,
    val money: Long,
    val depositAccount: Int
)
