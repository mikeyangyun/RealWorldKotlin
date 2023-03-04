package com.tw.yys.backendrealworld.interfaces.inbound

import com.tw.yys.backendrealworld.application.UserInfoModifyUseCase
import com.tw.yys.backendrealworld.interfaces.inbound.dto.CreateNewAccountRequest
import com.tw.yys.backendrealworld.interfaces.outbound.userInfo.response.UserInfoResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class UserInfoController(
//    private val userInfoQueryUseCase: UserInfoQueryUseCase,
    private val userInfoModifyUseCase: UserInfoModifyUseCase
) {
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    fun createNewAccount(
        @RequestBody request: CreateNewAccountRequest
    ): UserInfoResponse {
        return userInfoModifyUseCase.createNewAccount(request).toResponse()
    }
}
