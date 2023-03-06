package com.tw.yys.backendrealworld.domain.common.errors

class ExistingUserAccountInfoException: RealWorldException(
    message = "Username and email all have already been taken"
)
