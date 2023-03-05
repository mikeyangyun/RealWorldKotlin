package com.tw.yys.backendrealworld.application.userinfo

import com.tw.yys.backendrealworld.domain.UserInfoService
import com.tw.yys.backendrealworld.interfaces.inbound.dto.CreateNewAccountRequest
import com.tw.yys.backendrealworld.interfaces.inbound.dto.UpdateUserInfoRequest
import com.tw.yys.backendrealworld.interfaces.inbound.dto.UserInfoResponseDto
import org.springframework.stereotype.Service

@Service
class UserInfoModifyUseCase(
    private val userInfoService: UserInfoService
) {
    fun createNewAccount(command: CreateNewAccountRequest): UserInfoResponseDto {
        return userInfoService.createNewAccount(command).toDto()
    }

    fun updateUserInfo(userId: String, command: UpdateUserInfoRequest): UserInfoResponseDto {
        return userInfoService.updateUserInfo(userId, command).toDto()
    }

}
