package com.tw.yys.backendrealworld.interfaces.inbound.dto

import com.tw.yys.backendrealworld.interfaces.outbound.userInfo.UserInfoEntity

data class UpdateUserInfoRequest(
    val username: String,
    val email: String,
    val password: String,
    val bio: String?,
    val image: String?
) {
    fun toEntity(userId: String) = UserInfoEntity(
        id = userId,
        username = username,
        email = email,
        password = password,
        bio = bio,
        image = image
    )
}
