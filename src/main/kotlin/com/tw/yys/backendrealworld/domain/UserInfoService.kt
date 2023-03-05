package com.tw.yys.backendrealworld.domain

import com.tw.yys.backendrealworld.domain.common.errors.ExistingEmailException
import com.tw.yys.backendrealworld.domain.common.errors.ExistingUserAccountInfoException
import com.tw.yys.backendrealworld.domain.common.errors.ExistingUserNameException
import com.tw.yys.backendrealworld.interfaces.inbound.dto.CreateNewAccountRequest
import com.tw.yys.backendrealworld.interfaces.outbound.userInfo.UserInfoEntity
import org.springframework.stereotype.Service

@Service
class UserInfoService(
    private val repository: UserInfoRepository
) {
    fun createNewAccount(command: CreateNewAccountRequest): UserInfoEntity {
        val existingUsername = repository.findByUserName(command.username)
        val existingEmail = repository.findByEmail(command.email)

        if (existingUsername != null && existingEmail != null) throw ExistingUserAccountInfoException()
        if (existingUsername != null) throw ExistingUserNameException()
        if (existingEmail != null) throw ExistingEmailException()

        return repository.save(command.toEntity())
    }
}

interface UserInfoRepository {
    fun save(entity: UserInfoEntity): UserInfoEntity
    fun findByUserName(userName: String): UserInfoEntity?
    fun findByEmail(email: String): UserInfoEntity?

}
