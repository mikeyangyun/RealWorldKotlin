package com.tw.yys.backendrealworld.interfaces.outbound.userInfo

import com.tw.yys.backendrealworld.domain.UserInfoRepository
import org.springframework.stereotype.Component

@Component
class UserInfoRepositoryImpl(
    private val dao: UserInfoDao
): UserInfoRepository {
    override fun save(entity: UserInfoEntity): UserInfoEntity {
        return dao.save(entity.toPo()).toDomain()
    }

    override fun findByUserName(userName: String): UserInfoEntity? {
        return dao.findTopByUsername(userName)?.toDomain()
    }
    override fun findByEmail(email: String): UserInfoEntity? {
        return dao.findTopByEmail(email)?.toDomain()
    }

    override fun findUserById(userId: String): UserInfoEntity? {
        return dao.findTopById(userId)?.toDomain()
    }
}
