package com.tw.yys.backendrealworld.interfaces.inbound.dto

import com.tw.yys.backendrealworld.interfaces.outbound.userInfo.response.UserInfoResponse

data class UserInfoResponseDto(
    val username: String,
    val email: String,
    val bio: String?,
    val image: String?
){
    fun toResponse() = UserInfoResponse(this)
}
