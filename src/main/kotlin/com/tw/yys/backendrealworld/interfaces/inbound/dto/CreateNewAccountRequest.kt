package com.tw.yys.backendrealworld.interfaces.inbound.dto

import com.tw.yys.backendrealworld.interfaces.outbound.userInfo.UserInfoEntity

data class CreateNewAccountRequest(
    val username: String,
    val email: String,
    val password: String
) {
    fun toEntity(): UserInfoEntity = UserInfoEntity(
        username = username,
        email = email,
        password = password,
        bio = null,
        image = null
    )
}
