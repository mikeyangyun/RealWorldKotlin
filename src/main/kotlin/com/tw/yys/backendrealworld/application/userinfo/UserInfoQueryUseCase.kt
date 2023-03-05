package com.tw.yys.backendrealworld.application.userinfo

import com.tw.yys.backendrealworld.domain.UserInfoService
import com.tw.yys.backendrealworld.domain.common.errors.UserNotFoundException
import com.tw.yys.backendrealworld.interfaces.inbound.dto.UserInfoResponseDto
import org.springframework.stereotype.Service

@Service
class UserInfoQueryUseCase(
    private val userInfoService: UserInfoService
) {
    fun findUserById(userId: String): UserInfoResponseDto {
        return userInfoService.findUserById(userId)?.toDto() ?: throw UserNotFoundException()
    }
}
