package com.tw.yys.backendrealworld.interfaces.inbound

import com.tw.yys.backendrealworld.application.UserInfoModifyUseCase
import com.tw.yys.backendrealworld.interfaces.inbound.dto.CreateNewAccountRequest
import com.tw.yys.backendrealworld.interfaces.inbound.dto.UserInfoResponseDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserInfoController(
//    private val userInfoQueryUseCase: UserInfoQueryUseCase,
    private val userInfoModifyUseCase: UserInfoModifyUseCase
) {
    @PostMapping
    fun createNewAccount(
        @RequestBody request: CreateNewAccountRequest
    ): UserInfoResponseDto {
        return userInfoModifyUseCase.createNewAccount(request).toDto()
    }
}
