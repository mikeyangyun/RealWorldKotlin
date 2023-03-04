package com.tw.yys.backendrealworld.interfaces.inbound.dto

data class UserInfoResponseDto(
    val username: String,
    val email: String,
    val password: String,
    val bio: String?,
    val image: String?
)
