package com.tw.yys.backendrealworld.domain

import com.tw.yys.backendrealworld.domain.common.errors.ArticleNotFoundException
import com.tw.yys.backendrealworld.domain.common.errors.UserNotFoundException
import com.tw.yys.backendrealworld.fixture.ArticleFixture
import com.tw.yys.backendrealworld.fixture.UserInfoFixture
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
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
            every { userInfoRepository.findUserById(any()) } returns null
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

    @Nested
    inner class WhenUpdateArticle {
        private val request = ArticleFixture.Default.updateArticleRequest
        private val articleEntity = ArticleFixture.Default.articleEntity
        private val userInfoEntity = UserInfoFixture.Default.userInfoEntity

        @Test
        fun `should throw ArticleNotFoundException given article not found by articleId`() {
            every { articleRepository.findArticleById(any()) } returns null
            every { userInfoRepository.findUserById(any()) } returns null
            every { articleRepository.save(any()) } returns articleEntity

            assertThrows<ArticleNotFoundException> {
                service.updateArticle(1, request)
            }
            verify(inverse = true) {
                userInfoRepository.findUserById(any())
                articleRepository.save(any())
            }
        }

        @Test
        fun `should throw UserNotFoundException given user not found by userId`() {
            every { articleRepository.findArticleById(any()) } returns articleEntity
            every { userInfoRepository.findUserById(any()) } returns null
            every { articleRepository.save(any()) } returns articleEntity

            assertThrows<UserNotFoundException> {
                service.updateArticle(1, request)
            }
            verify {
                articleRepository.findArticleById(1)
            }
            verify(inverse = true) {
                articleRepository.save(any())
            }
        }

        @Test
        fun `should return updated article profile given update article successfully`() {
            every { articleRepository.findArticleById(any()) } returns articleEntity
            every { userInfoRepository.findUserById(any()) } returns userInfoEntity
            every { articleRepository.save(any()) } returns articleEntity

            val singleArticleProfileEntity = service.updateArticle(1, request)
            verify {
                articleRepository.save(any())
            }

            assertThat(singleArticleProfileEntity.content).isEqualTo(request.content)
        }
    }

    @Nested
    inner class WhenFindArticleById {
        private val articleEntity = ArticleFixture.Default.articleEntity
        private val userInfoEntity = UserInfoFixture.Default.userInfoEntity

        @Test
        fun `should throw ArticleNotFoundException given article not found by articleId`() {
            every { articleRepository.findArticleById(any()) } returns null
            every { userInfoRepository.findUserById(any()) } returns null

            assertThrows<ArticleNotFoundException> {
                service.findArticleById(1)
            }
            verify(inverse = true) {
                userInfoRepository.findUserById(any())
            }
        }

        @Test
        fun `should throw UserNotFoundException given user not found by userId`() {
            every { articleRepository.findArticleById(any()) } returns articleEntity
            every { userInfoRepository.findUserById(any()) } returns null

            assertThrows<UserNotFoundException> {
                service.findArticleById(1)
            }
            verify {
                articleRepository.findArticleById(1)
            }
        }

        @Test
        fun `should return found article profile given article exist`() {
            every { articleRepository.findArticleById(any()) } returns articleEntity
            every { userInfoRepository.findUserById(any()) } returns userInfoEntity

            val singleArticleProfileEntity = service.findArticleById(1)
            verify {
                articleRepository.findArticleById(any())
                userInfoRepository.findUserById(any())
            }

            assertThat(singleArticleProfileEntity.articleId).isEqualTo(1)

        }
    }

    @Nested
    inner class WhenDeleteArticleById {
        @Test
        fun `should throw ArticleNotFoundException given article not found by articleId`() {
            every { articleRepository.deleteArticleById(any()) } just runs

            service.deleteArticleById(1)

            verify{
                articleRepository.deleteArticleById(1)
            }
        }
    }

    @Nested
    inner class WhenRetrieveArticles {
        private val tag = "tag"
        private val authorName = "name"
        private val limit = 20
        private val offset = 1

        private val articleEntity = ArticleFixture.Default.articleEntity
        private val userInfoEntity = UserInfoFixture.Default.userInfoEntity

        @Test
        fun `should return empty list given tag and authorName not required when no articles exist`() {
            every {
                articleRepository.findAllArticlesLimitIsAndOffsetIs(any(), any())
            } returns emptyList()
            every {
                articleRepository.findAllArticlesByTagAndLimitIsAndOffsetIs(tag, any(), any())
            } returns emptyList()
            every {
                articleRepository.findAllArticlesByAuthorIdAndLimitIsAndOffsetIs(authorId, any(), any())
            } returns emptyList()
            every {
                articleRepository.findAllArticlesByTagAndAuthorIdAndLimitIsAndOffsetIs(tag, authorId, any(), any())
            } returns emptyList()

            val retrieveArticles = service.retrieveArticles("", "", limit, offset)

            assertThat(retrieveArticles).isEmpty()
            verify {
                articleRepository.findAllArticlesLimitIsAndOffsetIs(any(), any())
            }
            verify(inverse = true) {
                articleRepository.findAllArticlesByTagAndLimitIsAndOffsetIs(tag, any(), any())
                articleRepository.findAllArticlesByAuthorIdAndLimitIsAndOffsetIs(authorId, any(), any())
                articleRepository.findAllArticlesByTagAndAuthorIdAndLimitIsAndOffsetIs(tag, authorId, any(), any())
            }
        }
    }
}
