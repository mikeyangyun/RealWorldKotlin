package com.tw.yys.backendrealworld.interfaces.outbound.userInfo

import com.tw.yys.backendrealworld.interfaces.inbound.dto.UserInfoResponseDto

data class UserInfoModel(
    val id: String,
    val username: String,
    val email: String,
    val password: String,
    val bio: String?,
    val image: String?
) {
    fun toPo(): UserInfoPo = UserInfoPo(
        id = id,
        username = username,
        email = email,
        password = password,
        bio = bio,
        image = image
    )

    fun toDto(): UserInfoResponseDto = UserInfoResponseDto(
        username = username,
        email = email,
        bio = bio,
        image = image
    )
}

