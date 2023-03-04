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

}
