package com.tw.yys.backendrealworld.domain.common.errors

class ExistingUserNameException: RealWorldException(
    message = "Username has already been taken"
)
