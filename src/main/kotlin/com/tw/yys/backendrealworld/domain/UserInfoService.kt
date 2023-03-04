package com.tw.yys.backendrealworld.domain

import com.tw.yys.backendrealworld.interfaces.inbound.dto.CreateNewAccountRequest
import com.tw.yys.backendrealworld.interfaces.outbound.userInfo.UserInfoEntity
import org.springframework.stereotype.Service

@Service
class UserInfoService(
    private val repository: UserInfoRepository
) {
    fun createNewAccount(command: CreateNewAccountRequest): UserInfoEntity {
        return repository.save(command.toEntity())
    }
}

interface UserInfoRepository {
    fun save(entity: UserInfoEntity): UserInfoEntity
}
