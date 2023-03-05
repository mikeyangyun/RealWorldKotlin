package com.tw.yys.backendrealworld.fixture

import com.tw.yys.backendrealworld.interfaces.inbound.dto.CreateNewAccountRequest
import com.tw.yys.backendrealworld.interfaces.inbound.dto.UpdateUserInfoRequest
import com.tw.yys.backendrealworld.interfaces.inbound.dto.UserInfoResponseDto
import com.tw.yys.backendrealworld.interfaces.outbound.userInfo.UserInfoEntity

object UserInfoFixture {
    object Default {
        val createNewAccountRequest = CreateNewAccountRequest(
            username = "jack",
            email = "jack@gmail.com",
            password = "password"
        )
        val userInfoEntity = UserInfoEntity(
            id = "fake id for test",
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
        val updateUserInfoRequest = UpdateUserInfoRequest(
            username = "jack",
            email = "jack@gmail.com",
            password = "password",
            bio ="https://realWorld.bios",
            image = "image for user"
        )

        val updateUserInfoEntity = UserInfoEntity(
            id = "fake id for test",
            username = "jack",
            email = "jack@gmail.com",
            password = "password",
            bio ="https://realWorld.bios",
            image = "image for user"
        )


        val updateUserInfoResponseDto = UserInfoResponseDto(
            username = "jack",
            email = "jack@gmail.com",
            password = "password",
            bio ="https://realWorld.bios",
            image = "image for user"
        )
    }
}
