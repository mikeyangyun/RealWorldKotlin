package com.tw.yys.backendrealworld.domain.common.model

data class UserProfile(
    val username: String,
    val bio: String?,
    val image: String?,
    val following: Boolean = false
)
