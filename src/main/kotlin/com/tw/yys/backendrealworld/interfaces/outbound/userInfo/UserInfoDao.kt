package com.tw.yys.backendrealworld.interfaces.outbound.userInfo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserInfoDao : JpaRepository<UserInfoPo, String> {
}