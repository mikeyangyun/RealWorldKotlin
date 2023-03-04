package com.tw.yys.backendrealworld.application

import com.tw.yys.backendrealworld.domain.UserInfoService
import com.tw.yys.backendrealworld.interfaces.inbound.dto.CreateNewAccountRequest
import com.tw.yys.backendrealworld.interfaces.outbound.userInfo.UserInfoEntity
import org.springframework.stereotype.Service

@Service
class UserInfoModifyUseCase(
    private val userInfoService: UserInfoService
) {
    fun createNewAccount(command: CreateNewAccountRequest): UserInfoEntity {
        return userInfoService.createNewAccount(command)
    }

}
