package com.tw.yys.backendrealworld.domain.common.errors

class ExistingUserNameException: RealWorldException(
    username = listOf("has already been taken")
)
