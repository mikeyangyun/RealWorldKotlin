package com.tw.yys.backendrealworld.domain.common.errors

import java.lang.RuntimeException


open class RealWorldException(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause)
