package com.tw.yys.backendrealworld.domain.common.errors

class ExistingUserAccountInfoException: RealWorldException(
    username = listOf("has already been taken"),
    email = listOf("has already been taken")
)
