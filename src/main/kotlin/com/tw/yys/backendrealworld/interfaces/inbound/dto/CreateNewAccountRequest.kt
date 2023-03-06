package com.tw.yys.backendrealworld.interfaces.inbound.dto

import com.tw.yys.backendrealworld.domain.common.EntityId
import com.tw.yys.backendrealworld.interfaces.outbound.userInfo.UserInfoModel

data class CreateNewAccountRequest(
    val username: String,
    val email: String,
    val password: String
) {
    fun toEntity(): UserInfoModel = UserInfoModel(
        id = EntityId.newId().toString(),
        username = username,
        email = email,
        password = password,
        bio = null,
        image = null
    )
}
