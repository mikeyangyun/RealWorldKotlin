package com.tw.yys.backendrealworld.domain

import com.tw.yys.backendrealworld.domain.common.errors.UserNotFoundException
import com.tw.yys.backendrealworld.fixture.ArticleFixture
import com.tw.yys.backendrealworld.fixture.UserInfoFixture
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ArticleServiceTest{
    private val articleRepository = mockk<ArticleRepository>()
    private val userInfoRepository = mockk<UserInfoRepository>()
    @InjectMockKs
    private lateinit var service : ArticleService

    val authorId = "fake id for test"
    @Nested
    inner class WhenCreateNewArticle {
        private val request = ArticleFixture.Default.createNewArticleRequest
        private val articleEntity = ArticleFixture.Default.articleEntity
        private val userInfoEntity = UserInfoFixture.Default.userInfoEntity

        @Test
        fun `should throw UserNotFoundException given user not found by userId`() {
            every { userInfoRepository.findUserById(any()) } returns userInfoEntity
            every { articleRepository.save(articleEntity) } returns articleEntity

            assertThrows<UserNotFoundException> {
                service.createNewArticle(authorId, request)
            }
            verify(inverse = true) {
                articleRepository.save(any())
            }
        }

        @Test
        fun `should return created article profile given create article successfully`() {
            every { userInfoRepository.findUserById(any()) } returns userInfoEntity
            every { articleRepository.save(any()) } returns articleEntity

            val singleArticleProfileEntity = service.createNewArticle(authorId, request)
            verify {
                articleRepository.save(any())
            }
            assertThat(singleArticleProfileEntity.author.username).isEqualTo(userInfoEntity.username)
            assertThat(singleArticleProfileEntity.author.image).isEqualTo(userInfoEntity.image)
            assertThat(singleArticleProfileEntity.author.bio).isEqualTo(userInfoEntity.bio)

            assertThat(singleArticleProfileEntity.articleId).isEqualTo(articleEntity.articleId)
            assertThat(singleArticleProfileEntity.title).isEqualTo(articleEntity.title)
        }
    }


}
