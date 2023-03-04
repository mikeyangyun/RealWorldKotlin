package com.tw.yys.backendrealworld.fixture

import com.tw.yys.backendrealworld.interfaces.inbound.dto.CreateNewAccountRequest
import com.tw.yys.backendrealworld.interfaces.inbound.dto.UserInfoResponseDto
import com.tw.yys.backendrealworld.interfaces.outbound.userInfo.UserInfoEntity

object UserInfoFixture {
    object Default {
        val request = CreateNewAccountRequest(
            username = "jack",
            email = "jack@gmail.com",
            password = "password"
        )
        val userInfoEntity = UserInfoEntity(
            username = "jack",
            email = "jack@gmail.com",
            password = "password",
            bio = null,
            image = null
        )
        val userInfoResponseDto = UserInfoResponseDto(
            username = "jack",
            email = "jack@gmail.com",
            password = "password",
            bio = null,
            image = null
        )
    }
}
