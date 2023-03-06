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
class ArticleServiceTest {
    private val articleRepository = mockk<ArticleRepository>()
    private val userInfoRepository = mockk<UserInfoRepository>()

    @InjectMockKs
    private lateinit var service: ArticleService

    val authorId = "fake id for test"

    @Nested
    inner class WhenCreateNewArticle {
        private val request = ArticleFixture.Default.createNewArticleRequest
        private val articleModel = ArticleFixture.Default.articleModel
        private val userInfoModel = UserInfoFixture.Default.userInfoModel

        @Test
        fun `should throw UserNotFoundException given user not found by userId`() {
            every { userInfoRepository.findUserById(any()) } returns null
            every { articleRepository.save(articleModel) } returns articleModel

            assertThrows<UserNotFoundException> {
                service.createNewArticle(authorId, request)
            }
            verify(inverse = true) {
                articleRepository.save(any())
            }
        }

        @Test
        fun `should return created article profile given create article successfully`() {
            every { userInfoRepository.findUserById(any()) } returns userInfoModel
            every { articleRepository.save(any()) } returns articleModel

            val singleArticleProfileEntity = service.createNewArticle(authorId, request)
            verify {
                articleRepository.save(any())
            }
            assertThat(singleArticleProfileEntity.author.username).isEqualTo(userInfoModel.username)
            assertThat(singleArticleProfileEntity.author.image).isEqualTo(userInfoModel.image)
            assertThat(singleArticleProfileEntity.author.bio).isEqualTo(userInfoModel.bio)

            assertThat(singleArticleProfileEntity.articleId).isEqualTo(articleModel.articleId)
            assertThat(singleArticleProfileEntity.title).isEqualTo(articleModel.title)
        }
    }

    @Nested
    inner class WhenUpdateArticle {
        private val request = ArticleFixture.Default.updateArticleRequest
        private val articleEntity = ArticleFixture.Default.articleModel
        private val userInfoEntity = UserInfoFixture.Default.userInfoModel

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
        private val articleModel = ArticleFixture.Default.articleModel
        private val userInfoModel = UserInfoFixture.Default.userInfoModel

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
            every { articleRepository.findArticleById(any()) } returns articleModel
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
            every { articleRepository.findArticleById(any()) } returns articleModel
            every { userInfoRepository.findUserById(any()) } returns userInfoModel

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
        private val userId = "fake id"
        private val user = UserInfoFixture.Default.userInfoModel
        private val article = ArticleFixture.Default.articleModel

        @Test
        fun `should throw UserNotFoundException given user not found by id`() {
            every { userInfoRepository.findUserById(userId) } returns null
            every { articleRepository.findArticleById(any()) } returns null
            every { articleRepository.deleteArticleById(any()) } just runs

            assertThrows<UserNotFoundException> {
                service.deleteArticleById(1, userId)
            }

            verify {
                userInfoRepository.findUserById(userId)
            }
            verify(inverse = true) {
                articleRepository.findArticleById(1)
                articleRepository.deleteArticleById(1)
            }
        }

        @Test
        fun `should throw ArticleNotFoundException given article not found by articleId`() {
            every { userInfoRepository.findUserById(userId) } returns user
            every { articleRepository.findArticleById(any()) } returns null
            every { articleRepository.deleteArticleById(any()) } just runs


            assertThrows<ArticleNotFoundException> {
                service.deleteArticleById(1, userId)

            }
            verify {
                userInfoRepository.findUserById(userId)
                articleRepository.findArticleById(1)
            }
            verify(inverse = true) {
                articleRepository.deleteArticleById(1)
            }
        }

        @Test
        fun `should delete article successfully given user is the author`() {
            every { userInfoRepository.findUserById(userId) } returns user
            every { articleRepository.findArticleById(any()) } returns article
            every { articleRepository.deleteArticleById(any()) } just runs

            service.deleteArticleById(1, userId)

            verify {
                userInfoRepository.findUserById(userId)
                articleRepository.findArticleById(1)
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

        private val userInfoModel = UserInfoFixture.Default.userInfoModel
        private val articleModel = ArticleFixture.Default.articleModel
        private val articleProfileModel = ArticleFixture.Default.articleProfileModel

        @Test
        fun `should return empty list given tag and authorName not required and no articles exist`() {
            every {
                articleRepository.findAllArticlesLimitIsAndOffsetIs(any(), any())
            } returns emptyList()

            every { service.findArticleById(any()) } returns articleProfileModel

            val retrieveArticles = service.retrieveArticles("", "", limit, offset)

            assertThat(retrieveArticles).isEmpty()
            verify {
                articleRepository.findAllArticlesLimitIsAndOffsetIs(any(), any())
            }
            verify(inverse = true) {
                articleRepository.findAllArticlesByTagAndLimitIsAndOffsetIs(any(), any(), any())
                articleRepository.findAllArticlesByAuthorIdAndLimitIsAndOffsetIs(any(), any(), any())
                articleRepository.findAllArticlesByTagAndAuthorIdAndLimitIsAndOffsetIs(any(), any(), any(), any())
            }
        }

        @Test
        fun `should return found articles given tag and authorName not required and articles exist`() {
            every {
                articleRepository.findAllArticlesLimitIsAndOffsetIs(any(), any())
            } returns listOf(articleModel)


            val articleService = mockkClass(ArticleService::class)
            every { articleService.findArticleById(any()) } returns articleProfileModel
            every { articleRepository.findArticleById(any()) } returns articleModel
            every { userInfoRepository.findUserById(any()) } returns userInfoModel

            val retrieveArticles = service.retrieveArticles("", "", limit, offset)

            assertThat(retrieveArticles).isNotEmpty
            verify {
                articleRepository.findAllArticlesLimitIsAndOffsetIs(any(), any())
            }
            verify(inverse = true) {
                articleRepository.findAllArticlesByTagAndLimitIsAndOffsetIs(any(), any(), any())
                articleRepository.findAllArticlesByAuthorIdAndLimitIsAndOffsetIs(any(), any(), any())
                articleRepository.findAllArticlesByTagAndAuthorIdAndLimitIsAndOffsetIs(any(), any(), any(), any())
            }
        }

        @Test
        fun `should return empty list given tag required and authorName not required when no articles exist`() {
            every {
                articleRepository.findAllArticlesByTagAndLimitIsAndOffsetIs(tag, any(), any())
            } returns emptyList()

            every { service.findArticleById(any()) } returns articleProfileModel

            val retrieveArticles = service.retrieveArticles(tag, "", limit, offset)

            assertThat(retrieveArticles).isEmpty()
            verify {
                articleRepository.findAllArticlesByTagAndLimitIsAndOffsetIs(tag, any(), any())
            }
            verify(inverse = true) {
                articleRepository.findAllArticlesLimitIsAndOffsetIs(any(), any())
                articleRepository.findAllArticlesByAuthorIdAndLimitIsAndOffsetIs(any(), any(), any())
                articleRepository.findAllArticlesByTagAndAuthorIdAndLimitIsAndOffsetIs(any(), any(), any(), any())
            }
        }

        @Test
        fun `should return found articles given tag required and authorName not required when articles exist`() {
            every {
                articleRepository.findAllArticlesByTagAndLimitIsAndOffsetIs(tag, any(), any())
            } returns listOf(articleModel)

            val articleService = mockkClass(ArticleService::class)
            every { articleService.findArticleById(any()) } returns articleProfileModel
            every { articleRepository.findArticleById(any()) } returns articleModel
            every { userInfoRepository.findUserById(any()) } returns userInfoModel

            val retrieveArticles = service.retrieveArticles(tag, "", limit, offset)

            assertThat(retrieveArticles).isNotEmpty
            verify {
                articleRepository.findAllArticlesByTagAndLimitIsAndOffsetIs(tag, any(), any())
            }
            verify(inverse = true) {
                articleRepository.findAllArticlesLimitIsAndOffsetIs(any(), any())
                articleRepository.findAllArticlesByAuthorIdAndLimitIsAndOffsetIs(any(), any(), any())
                articleRepository.findAllArticlesByTagAndAuthorIdAndLimitIsAndOffsetIs(any(), any(), any(), any())
            }
        }

        @Test
        fun `should return empty list given tag not required and authorName required when no articles exist`() {
            every {
                articleRepository.findAllArticlesByAuthorIdAndLimitIsAndOffsetIs(authorId, any(), any())
            } returns emptyList()

            every { userInfoRepository.findByUserName(any()) } returns userInfoModel
            every { service.findArticleById(any()) } returns articleProfileModel

            val retrieveArticles = service.retrieveArticles("", authorName, limit, offset)

            assertThat(retrieveArticles).isEmpty()
            verify {
                articleRepository.findAllArticlesByAuthorIdAndLimitIsAndOffsetIs(authorId, any(), any())
            }
            verify(inverse = true) {
                articleRepository.findAllArticlesLimitIsAndOffsetIs(any(), any())
                articleRepository.findAllArticlesByTagAndLimitIsAndOffsetIs(any(), any(), any())
                articleRepository.findAllArticlesByTagAndAuthorIdAndLimitIsAndOffsetIs(any(), any(), any(), any())
            }
        }

        @Test
        fun `should return found articles given tag not required and authorName required when articles exist`() {
            every {
                articleRepository.findAllArticlesByAuthorIdAndLimitIsAndOffsetIs(authorId, any(), any())
            } returns listOf(articleModel)

            val articleService = mockkClass(ArticleService::class)
            every { articleService.findArticleById(any()) } returns articleProfileModel
            every { articleRepository.findArticleById(any()) } returns articleModel
            every { userInfoRepository.findUserById(any()) } returns userInfoModel
            every { userInfoRepository.findByUserName(any()) } returns userInfoModel

            val retrieveArticles = service.retrieveArticles("", authorName, limit, offset)

            assertThat(retrieveArticles).isNotEmpty
            verify {
                articleRepository.findAllArticlesByAuthorIdAndLimitIsAndOffsetIs(authorId, any(), any())
            }
            verify(inverse = true) {
                articleRepository.findAllArticlesLimitIsAndOffsetIs(any(), any())
                articleRepository.findAllArticlesByTagAndLimitIsAndOffsetIs(any(), any(), any())
                articleRepository.findAllArticlesByTagAndAuthorIdAndLimitIsAndOffsetIs(any(), any(), any(), any())
            }
        }

        @Test
        fun `should return empty list given tag required and authorName required when no articles exist and user not found`() {
            every {
                articleRepository.findAllArticlesByTagAndAuthorIdAndLimitIsAndOffsetIs(tag, authorId, any(), any())
            } returns emptyList()


            every { userInfoRepository.findByUserName(any()) } returns null
            every { service.findArticleById(any()) } returns articleProfileModel

            val retrieveArticles = service.retrieveArticles(tag, authorName, limit, offset)

            assertThat(retrieveArticles).isEmpty()
            verify(inverse = true) {
                articleRepository.findAllArticlesLimitIsAndOffsetIs(any(), any())
                articleRepository.findAllArticlesByTagAndLimitIsAndOffsetIs(any(), any(), any())
                articleRepository.findAllArticlesByAuthorIdAndLimitIsAndOffsetIs(any(), any(), any())
                articleRepository.findAllArticlesByTagAndAuthorIdAndLimitIsAndOffsetIs(any(), any(), any(), any())
            }
        }

        @Test
        fun `should return empty list given tag required and authorName required when no articles exist and user found`() {
            every {
                articleRepository.findAllArticlesByTagAndAuthorIdAndLimitIsAndOffsetIs(tag, authorId, any(), any())
            } returns emptyList()


            every { userInfoRepository.findByUserName(any()) } returns userInfoModel
            every { service.findArticleById(any()) } returns articleProfileModel

            val retrieveArticles = service.retrieveArticles(tag, authorName, limit, offset)

            assertThat(retrieveArticles).isEmpty()
            verify(inverse = true) {
                articleRepository.findAllArticlesLimitIsAndOffsetIs(any(), any())
                articleRepository.findAllArticlesByTagAndLimitIsAndOffsetIs(any(), any(), any())
                articleRepository.findAllArticlesByAuthorIdAndLimitIsAndOffsetIs(any(), any(), any())
            }
            verify {
                articleRepository.findAllArticlesByTagAndAuthorIdAndLimitIsAndOffsetIs(tag, authorId, any(), any())
            }
        }

        @Test
        fun `should return found articles given tag required and authorName required when articles exist`() {
            every {
                articleRepository.findAllArticlesByTagAndAuthorIdAndLimitIsAndOffsetIs(tag, authorId, any(), any())
            } returns listOf(articleModel)

            val articleService = mockkClass(ArticleService::class)
            every { articleService.findArticleById(any()) } returns articleProfileModel
            every { articleRepository.findArticleById(any()) } returns articleModel
            every { userInfoRepository.findByUserName(any()) } returns userInfoModel
            every { userInfoRepository.findUserById(any()) } returns userInfoModel

            val retrieveArticles = service.retrieveArticles(tag, authorName, limit, offset)

            assertThat(retrieveArticles).isNotEmpty
            verify(inverse = true) {
                articleRepository.findAllArticlesLimitIsAndOffsetIs(any(), any())
                articleRepository.findAllArticlesByTagAndLimitIsAndOffsetIs(any(), any(), any())
                articleRepository.findAllArticlesByAuthorIdAndLimitIsAndOffsetIs(any(), any(), any())
            }
            verify {
                articleRepository.findAllArticlesByTagAndAuthorIdAndLimitIsAndOffsetIs(tag, authorId, any(), any())
            }
        }


    }
}
