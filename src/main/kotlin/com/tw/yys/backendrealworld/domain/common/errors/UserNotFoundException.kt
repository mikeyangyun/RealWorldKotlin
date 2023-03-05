package com.tw.yys.backendrealworld.domain.common.errors

class UserNotFoundException: RealWorldException(
    message = "This user is not found"
)
