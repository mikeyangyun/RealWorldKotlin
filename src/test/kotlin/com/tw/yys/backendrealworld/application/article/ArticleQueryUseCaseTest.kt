package com.tw.yys.backendrealworld.application.article

import com.tw.yys.backendrealworld.domain.ArticleService
import com.tw.yys.backendrealworld.fixture.ArticleFixture
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ArticleQueryUseCaseTest{
    private val articleService = mockk<ArticleService>()

    @InjectMockKs
    private lateinit var queryUseCase: ArticleQueryUseCase

    @Nested
    inner class WhenFindArticleById{
        private val articleProfileModel = ArticleFixture.Default.articleProfileModel
        @Test
        fun `should get status is OK given article exist`() {
            every { articleService.findArticleById(any()) } returns articleProfileModel

            val responseDto = queryUseCase.findArticleById(1)

            assertThat(responseDto).isEqualTo(articleProfileModel.toDto())
        }
    }

    @Nested
    inner class WhenRetrieveArticles{
        private val articleProfileModel = ArticleFixture.Default.articleProfileModel

        @Test
        fun `should return multiple articles successfully given article exist`(){
            every { articleService.retrieveArticles(any(),any(), any(), any()) } returns listOf(articleProfileModel)

            val retrieveArticles = articleService.retrieveArticles("tag", "name", 20, 0)

            assertThat(retrieveArticles).isEqualTo(listOf(articleProfileModel))
        }
    }

}
