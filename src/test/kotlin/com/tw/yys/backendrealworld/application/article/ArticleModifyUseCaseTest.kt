package com.tw.yys.backendrealworld.application.article

import com.tw.yys.backendrealworld.domain.ArticleService
import com.tw.yys.backendrealworld.fixture.ArticleFixture
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ArticleModifyUseCaseTest {
    private val articleService = mockk<ArticleService>()

    @InjectMockKs
    private lateinit var modifyUseCase: ArticleModifyUseCase

    val userId = "fake id"

    @Nested
    inner class WhenCreateNewArticle{
        private val request = ArticleFixture.Default.createNewArticleRequest
        private val entity = ArticleFixture.Default.singleArticleProfileEntity
        @Test
        fun `should create new article given successfully called`(){
            every { articleService.createNewArticle(userId, request) } returns entity

            val responseDto = modifyUseCase.createNewArticle(userId, request)

            assertThat(responseDto).isEqualTo(entity.toDto())
        }
    }
    @Nested
    inner class WhenUpdateArticle{
        private val request = ArticleFixture.Default.updateArticleRequest
        private val entity = ArticleFixture.Default.singleArticleProfileEntity
        @Test
        fun `should update article successfully given article exist`(){
            every { articleService.updateArticle(any(), request) } returns entity

            val responseDto = modifyUseCase.updateArticle(1, request)

            assertThat(responseDto).isEqualTo(entity.toDto())
        }
    }

    @Nested
    inner class WhenDeleteArticle{
        @Test
        fun `should delete article successfully given article exist`(){
            every { articleService.deleteArticleById(any()) } just runs

            modifyUseCase.deleteArticleById(1)

            verify {
                articleService.deleteArticleById(1)
            }
        }
    }

}
