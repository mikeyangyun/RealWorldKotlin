package com.tw.yys.backendrealworld.interfaces.outbound.userInfo

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user_info")
data class UserInfoPo(
    @Id val id: String,

    @Column(name = "username") val username: String,
    @Column(name = "email") val email: String,
    @Column(name = "password") val password: String,
    @Column(name = "bio") var bio: String?,
    @Column(name = "image") var image: String?,
) {
    fun toDomain() = UserInfoModel(
        id = id,
        username = username,
        email = email,
        password = password,
        bio = bio,
        image = image
    )
}
