package com.tw.yys.backendrealworld.application.userinfo

import com.tw.yys.backendrealworld.domain.UserInfoService
import com.tw.yys.backendrealworld.domain.common.errors.UserNotFoundException
import com.tw.yys.backendrealworld.fixture.UserInfoFixture
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class UserInfoQueryUseCaseTest{
    private val userInfoService = mockk<UserInfoService>()

    @InjectMockKs
    private lateinit var queryUseCase: UserInfoQueryUseCase

    @Nested
    inner class WhenFindUserById {
        private val userId = "fake id for user"
        private val entity = UserInfoFixture.Default.userInfoEntity
        private val responseDto = UserInfoFixture.Default.userInfoResponseDto

        @Test
        fun `should return found userInfo given user exist`(){
            every { userInfoService.findUserById(userId) } returns entity

            val createNewAccount = queryUseCase.findUserById(userId)

            Assertions.assertEquals(responseDto, createNewAccount)
        }

        @Test
        fun `should throw UserNotFoundException given user not exist`(){
            every { userInfoService.findUserById(userId) } returns null

            assertThrows<UserNotFoundException> {
                queryUseCase.findUserById(userId)
            }
        }
    }
}
