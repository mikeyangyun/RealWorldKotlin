package com.tw.yys.backendrealworld.application.userinfo

import com.tw.yys.backendrealworld.domain.UserInfoService
import com.tw.yys.backendrealworld.fixture.UserInfoFixture
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class UserInfoModifyUseCaseTest {
    private val userInfoService = mockk<UserInfoService>()

    @InjectMockKs
    private lateinit var modifyUseCase: UserInfoModifyUseCase

    @Nested
    inner class WhenCreateNewAccount{
        private val request = UserInfoFixture.Default.createNewAccountRequest
        private val userInfoModel = UserInfoFixture.Default.userInfoModel
        private val responseDto = UserInfoFixture.Default.userInfoResponseDto

        @Test
        fun `should a new account info given successfully created`(){
            every { userInfoService.createNewAccount(request) } returns userInfoModel

            val createNewAccount = modifyUseCase.createNewAccount(request)

            assertEquals(responseDto, createNewAccount)
        }
    }

    @Nested
    inner class WhenUpdateUserInfo{
        private val id = "fake id for test"
        private val request = UserInfoFixture.Default.updateUserInfoRequest
        private val userInfoModel = UserInfoFixture.Default.updateUserInfoModel
        private val responseDto = UserInfoFixture.Default.updateUserInfoResponseDto

        @Test
        fun `should a new account info given successfully created`(){
            every { userInfoService.updateUserInfo(id, request) } returns userInfoModel

            val updatedUserInfo = modifyUseCase.updateUserInfo(id, request)

            assertEquals(responseDto, updatedUserInfo)
        }
    }
}
