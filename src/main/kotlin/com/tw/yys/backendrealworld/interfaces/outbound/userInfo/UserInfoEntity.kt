package com.tw.yys.backendrealworld.interfaces.outbound.userInfo

import com.tw.yys.backendrealworld.domain.common.EntityId
import com.tw.yys.backendrealworld.interfaces.inbound.dto.UserInfoResponseDto

data class UserInfoEntity(
    val username: String,
    val email: String,
    val password: String,
    val bio: String?,
    val image: String?
) {
    fun toPo(): UserInfoPo = UserInfoPo(
        id = EntityId.newId().toString(),
        username = username,
        email = email,
        password = password,
        bio = bio,
        image = image
    )

    fun toDto(): UserInfoResponseDto = UserInfoResponseDto(
        username = username,
        email = email,
        password = password,
        bio = bio,
        image = image
    )
}

