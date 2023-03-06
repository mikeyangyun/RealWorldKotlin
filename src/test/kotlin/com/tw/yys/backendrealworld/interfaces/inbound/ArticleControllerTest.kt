package com.tw.yys.backendrealworld.interfaces.inbound

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.tw.yys.backendrealworld.application.article.ArticleModifyUseCase
import com.tw.yys.backendrealworld.application.article.ArticleQueryUseCase
import com.tw.yys.backendrealworld.fixture.ArticleFixture
import com.tw.yys.backendrealworld.interfaces.inbound.dto.MultipleArticlesResponseDto
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@WebMvcTest(ArticleController::class)
class ArticleControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockkBean
    private lateinit var modifyUseCase: ArticleModifyUseCase

    @MockkBean
    private lateinit var queryUseCase: ArticleQueryUseCase

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }
    val userId = "fake id"

    @Nested
    inner class WhenCreateNewArticle{
        private val request = ArticleFixture.Default.createNewArticleRequest
        private val responseDto = ArticleFixture.Default.singleArticleProfileResponseDto
        @Test
        fun `should create new article given successfully called`(){
            every { modifyUseCase.createNewArticle(userId, request) } returns responseDto

            mockMvc.post("/api/articles") {
                param("userId", userId)
                content = objectMapper.writeValueAsString(request)
                contentType = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isOk() }
                content { responseDto.toResponse() }
            }
        }
    }

    @Nested
    inner class WhenUpdateArticle{
        private val request = ArticleFixture.Default.updateArticleRequest
        private val responseDto = ArticleFixture.Default.updateArticleResponseDto
        @Test
        fun `should update article successfully given article exist`(){
            every { modifyUseCase.updateArticle(any(), request) } returns responseDto

            mockMvc.post("/api/articles/1") {
                content = objectMapper.writeValueAsString(request)
                contentType = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isOk() }
                content { responseDto.toResponse() }
            }
        }
    }

    @Nested
    inner class WhenFindArticleById{
        private val responseDto = ArticleFixture.Default.singleArticleProfileResponseDto
        @Test
        fun `should get status is OK given article exist`(){
            every { queryUseCase.findArticleById(any()) } returns responseDto

            mockMvc.get("/api/articles/1") {
                contentType = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isOk() }
                content { responseDto.toResponse() }
            }
        }
    }

    @Nested
    inner class WhenDeleteArticle{
        @Test
        fun `should delete article successfully given article exist`(){
            every { modifyUseCase.deleteArticleById(any(), any()) } just runs

            mockMvc.delete("/api/articles/1") {
                param("userId", userId)
                contentType = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isOk() }
            }
        }
    }

    @Nested
    inner class WhenRetrieveArticles{
        private val dto = ArticleFixture.Default.singleArticleProfileResponseDto
        private val responseDto = MultipleArticlesResponseDto(
            listOf(dto),
            1
        )
        @Test
        fun `should return multiple articles successfully given article exist`(){
            every { queryUseCase.retrieveArticles(any(),any(), any(), any()) } returns responseDto

            mockMvc.get("/api/articles") {
                contentType = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isOk() }
            }
        }
    }




}
