package com.staker4wapper.flick_kiosk.data.dto

data class ProductResponse(
    val accountId: Int,
    val value: Int,
    val name: String,
    val imageUrl: String,
    val count: Int,
    val id: Int
)
