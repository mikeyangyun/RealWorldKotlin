package com.tw.yys.backendrealworld.domain

import com.tw.yys.backendrealworld.domain.common.errors.ExistingEmailException
import com.tw.yys.backendrealworld.domain.common.errors.ExistingUserAccountInfoException
import com.tw.yys.backendrealworld.domain.common.errors.ExistingUserNameException
import com.tw.yys.backendrealworld.fixture.UserInfoFixture
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class UserInfoServiceTest{
    private val repository = mockk<UserInfoRepository>()
    @InjectMockKs
    private lateinit var service : UserInfoService

    @Nested
    inner class WhenCreateNewAccount{
        private val request = UserInfoFixture.Default.request
        private val entity = UserInfoFixture.Default.userInfoEntity
        @Test
        fun `should throw ExistingUserNameException given username already taken and email not taken`() {
            every { repository.findByUserName(request.username) } returns entity
            every { repository.findByEmail(request.email) } returns null
            every { repository.save(any()) } returns entity

            assertThrows<ExistingUserNameException> {
                service.createNewAccount(request)
            }
            verify(inverse = true) {
                repository.save(any())
            }
        }

        @Test
        fun `should throw ExistingEmailException given username not taken and email already taken`() {
            every { repository.findByUserName(request.username) } returns null
            every { repository.findByEmail(request.email) } returns entity
            every { repository.save(any()) } returns entity

            assertThrows<ExistingEmailException> {
                service.createNewAccount(request)
            }
            verify(inverse = true) {
                repository.save(any())
            }
        }

        @Test
        fun `should throw ExistingUserAccountInfoException given username already taken and email already taken`() {
            every { repository.findByUserName(request.username) } returns entity
            every { repository.findByEmail(request.email) } returns entity
            every { repository.save(any()) } returns entity

            assertThrows<ExistingUserAccountInfoException> {
                service.createNewAccount(request)
            }
            verify(inverse = true) {
                repository.save(any())
            }
        }

        @Test
        fun `should create a new account successfully given no existing userAccount`() {
            every { repository.findByUserName(request.username) } returns null
            every { repository.findByEmail(request.email) } returns null
            every { repository.save(any()) } returns entity

            val newAccount = service.createNewAccount(request)

            assertThat(newAccount).isEqualTo(entity)
        }
    }

    @Nested
    inner class WhenFindUserById {
        private val userId = "fake id for user"
        private val entity = UserInfoFixture.Default.userInfoEntity
        private val responseDto = UserInfoFixture.Default.userInfoResponseDto

        @Test
        fun `should return found userInfo given user exist`(){
            every { repository.findUserById(userId) } returns entity

            val createNewAccount = service.findUserById(userId)

            Assertions.assertEquals(responseDto, createNewAccount)
        }
    }
}
