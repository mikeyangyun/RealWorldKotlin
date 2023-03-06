package com.tw.yys.backendrealworld.interfaces.inbound.dto

data class UpdateUserInfoRequest(
    val email: String?,
    val bio: String?,
    val image: String?
)
