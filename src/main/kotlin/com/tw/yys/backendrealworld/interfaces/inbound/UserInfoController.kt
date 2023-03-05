package com.tw.yys.backendrealworld.interfaces.inbound

import com.tw.yys.backendrealworld.application.userinfo.UserInfoModifyUseCase
import com.tw.yys.backendrealworld.application.userinfo.UserInfoQueryUseCase
import com.tw.yys.backendrealworld.interfaces.inbound.dto.CreateNewAccountRequest
import com.tw.yys.backendrealworld.interfaces.inbound.dto.UpdateUserInfoRequest
import com.tw.yys.backendrealworld.interfaces.outbound.userInfo.response.UserInfoResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class UserInfoController(
    private val userInfoQueryUseCase: UserInfoQueryUseCase,
    private val userInfoModifyUseCase: UserInfoModifyUseCase
) {
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    fun createNewAccount(
        @RequestBody request: CreateNewAccountRequest
    ): UserInfoResponse {
        return userInfoModifyUseCase.createNewAccount(request).toResponse()
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    fun findUserById(@RequestParam userId: String): UserInfoResponse {
        return userInfoQueryUseCase.findUserById(userId).toResponse()
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    fun updateUserInfo(
        @RequestParam userId: String,
        @RequestBody request: UpdateUserInfoRequest
    ): UserInfoResponse {
        return userInfoModifyUseCase.updateUserInfo(userId, request).toResponse()
    }


}
