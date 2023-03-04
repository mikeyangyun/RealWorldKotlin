package com.tw.yys.backendrealworld.domain.common.errors

class ExistingEmailException: RealWorldException(
    email = listOf("has already been taken")
)
