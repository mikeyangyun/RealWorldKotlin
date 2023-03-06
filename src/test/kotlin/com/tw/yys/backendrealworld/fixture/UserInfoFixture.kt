package com.tw.yys.backendrealworld.fixture

import com.tw.yys.backendrealworld.interfaces.inbound.dto.CreateNewAccountRequest
import com.tw.yys.backendrealworld.interfaces.inbound.dto.UpdateUserInfoRequest
import com.tw.yys.backendrealworld.interfaces.inbound.dto.UserInfoResponseDto
import com.tw.yys.backendrealworld.interfaces.outbound.userInfo.UserInfoModel

object UserInfoFixture {
    object Default {
        val createNewAccountRequest = CreateNewAccountRequest(
            username = "jack",
            email = "jack@gmail.com",
            password = "password"
        )
        val userInfoModel = UserInfoModel(
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
            bio = null,
            image = null
        )
        val updateUserInfoRequest = UpdateUserInfoRequest(
            email = "jack@gmail.com",
            bio ="https://realWorld.bios",
            image = "image for user"
        )

        val updateUserInfoModel = UserInfoModel(
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
            bio ="https://realWorld.bios",
            image = "image for user"
        )
    }
}
