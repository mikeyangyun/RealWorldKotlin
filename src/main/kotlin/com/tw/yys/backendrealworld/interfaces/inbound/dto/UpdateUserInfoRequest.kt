package com.tw.yys.backendrealworld.interfaces.inbound.dto

import com.tw.yys.backendrealworld.interfaces.outbound.userInfo.UserInfoModel

data class UpdateUserInfoRequest(
    val username: String,
    val email: String,
    val password: String,
    val bio: String?,
    val image: String?
) {
    fun toEntity(userId: String) = UserInfoModel(
        id = userId,
        username = username,
        email = email,
        password = password,
        bio = bio,
        image = image
    )
}
