package com.tw.yys.backendrealworld.domain.common.errors

class ExistingEmailException: RealWorldException(
    message = "Email has already been taken"
)
