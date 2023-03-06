package com.tw.yys.backendrealworld.domain

import com.tw.yys.backendrealworld.domain.common.errors.ExistingEmailException
import com.tw.yys.backendrealworld.domain.common.errors.ExistingUserAccountInfoException
import com.tw.yys.backendrealworld.domain.common.errors.ExistingUserNameException
import com.tw.yys.backendrealworld.domain.common.errors.UserNotFoundException
import com.tw.yys.backendrealworld.interfaces.inbound.dto.CreateNewAccountRequest
import com.tw.yys.backendrealworld.interfaces.inbound.dto.UpdateUserInfoRequest
import com.tw.yys.backendrealworld.interfaces.outbound.userInfo.UserInfoModel
import org.springframework.stereotype.Service

@Service
class UserInfoService(
    private val repository: UserInfoRepository
) {
    fun createNewAccount(command: CreateNewAccountRequest): UserInfoModel {
        val existingUsername = repository.findByUserName(command.username)
        val existingEmail = repository.findByEmail(command.email)

        if (existingUsername != null && existingEmail != null) throw ExistingUserAccountInfoException()
        if (existingUsername != null) throw ExistingUserNameException()
        if (existingEmail != null) throw ExistingEmailException()

        return repository.save(command.toEntity())
    }

    fun findUserById(userId: String): UserInfoModel? {
        return repository.findUserById(userId)
    }

    fun updateUserInfo(userId: String, command: UpdateUserInfoRequest): UserInfoModel {
        val existing = repository.findUserById(userId) ?: throw UserNotFoundException()

        val entity = existing.update(command)

        return repository.save(entity)

    }
}

interface UserInfoRepository {
    fun save(entity: UserInfoModel): UserInfoModel
    fun findByUserName(userName: String): UserInfoModel?
    fun findByEmail(email: String): UserInfoModel?
    fun findUserById(userId: String): UserInfoModel?
}
