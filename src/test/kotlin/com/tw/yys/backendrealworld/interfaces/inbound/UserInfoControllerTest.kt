package com.tw.yys.backendrealworld.interfaces.inbound

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.tw.yys.backendrealworld.application.userinfo.UserInfoModifyUseCase
import com.tw.yys.backendrealworld.application.userinfo.UserInfoQueryUseCase
import com.tw.yys.backendrealworld.fixture.UserInfoFixture
import io.mockk.clearAllMocks
import io.mockk.every
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@WebMvcTest(UserInfoController::class)
class UserInfoControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockkBean
    private lateinit var modifyUseCase: UserInfoModifyUseCase

    @MockkBean
    private lateinit var queryUseCase: UserInfoQueryUseCase

    private var accountId = "mock id for test"

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Nested
    inner class WhenCreateNewAccount{
        private val request = UserInfoFixture.Default.createNewAccountRequest
        private val responseDto = UserInfoFixture.Default.userInfoResponseDto
        @Test
        fun `should create new account given new sign up request`(){
            every { modifyUseCase.createNewAccount(request) } returns responseDto

            mockMvc.post("/users") {
                content = objectMapper.writeValueAsString(request)
                contentType = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isOk() }
                content { responseDto.toResponse() }
            }
        }
    }

    @Nested
    inner class WhenFindUserById{
        private val responseDto = UserInfoFixture.Default.userInfoResponseDto
        @Test
        fun `should return found user when user found successfully`(){
            every { queryUseCase.findUserById(accountId) } returns responseDto

            mockMvc.get("/user") {
                param("userId", accountId)
            }.andExpect {
                status { isOk() }
                content { objectMapper.writeValueAsString(responseDto.toResponse()) }
            }
        }
    }

    @Nested
    inner class WhenUpdateUserInfo{
        private val id = "fake id for test"
        private val request = UserInfoFixture.Default.updateUserInfoRequest
        private val responseDto = UserInfoFixture.Default.updateUserInfoResponseDto

        @Test
        fun `should return updated user when user info update successfully`(){
            every { modifyUseCase.updateUserInfo(id, request) } returns responseDto

            mockMvc.post("/user") {
                param("userId", id)
                content = objectMapper.writeValueAsString(request)
                contentType = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isOk() }
                content { objectMapper.writeValueAsString(responseDto.toResponse()) }
            }
        }
    }

}
