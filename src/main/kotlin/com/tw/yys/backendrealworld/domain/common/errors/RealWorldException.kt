package com.tw.yys.backendrealworld.domain.common.errors

import java.lang.RuntimeException


open class RealWorldException(
    val username: List<String>? = null,
    val email: List<String>? = null,
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause)
