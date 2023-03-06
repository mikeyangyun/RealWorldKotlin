package com.tw.yys.backendrealworld.interfaces.inbound

import com.tw.yys.backendrealworld.domain.common.errors.ExistingUserNameException
import com.tw.yys.backendrealworld.domain.common.errors.ExistingUserAccountInfoException
import com.tw.yys.backendrealworld.domain.common.errors.ArticleNotFoundException
import com.tw.yys.backendrealworld.domain.common.errors.UserNotFoundException
import com.tw.yys.backendrealworld.domain.common.errors.ExistingEmailException
import com.tw.yys.backendrealworld.domain.common.errors.RealWorldException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestControllerAdvice {

    @ExceptionHandler(RealWorldException::class)
    fun handleRealWorldException(exception: RealWorldException): ResponseEntity<ErrorResponseDto> =
        createErrorResponseEntity(exception)

    private fun createErrorResponseEntity(exception: RealWorldException): ResponseEntity<ErrorResponseDto> =
        ResponseEntity(ErrorResponseDto(exception), createErrorResponseStatus(exception))

    private fun createErrorResponseStatus(exception: Exception): HttpStatus =
        when (exception) {
            is ArticleNotFoundException -> HttpStatus.NOT_FOUND
            is ExistingEmailException -> HttpStatus.BAD_REQUEST
            is ExistingUserNameException -> HttpStatus.BAD_REQUEST
            is UserNotFoundException -> HttpStatus.NOT_FOUND
            is ExistingUserAccountInfoException -> HttpStatus.BAD_REQUEST
            else -> HttpStatus.INTERNAL_SERVER_ERROR
        }

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<ErrorResponseDto> =
        ResponseEntity(ErrorResponseDto(exception), HttpStatus.INTERNAL_SERVER_ERROR)
}


data class ErrorResponseDto(
    val errors: ErrorResponseDtoNested
) {
    constructor(exception: Exception) : this(
        ErrorResponseDtoNested(
            exception.message?.let { listOf(it) } ?: emptyList()
        )
    )
    data class ErrorResponseDtoNested(
        val body: Collection<String>
    )
}
