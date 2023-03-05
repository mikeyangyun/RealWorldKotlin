package com.tw.yys.backendrealworld.fixture

import com.tw.yys.backendrealworld.domain.common.model.UserProfile
import com.tw.yys.backendrealworld.interfaces.inbound.dto.*
import com.tw.yys.backendrealworld.interfaces.outbound.article.response.SingleArticleProfileEntity
import java.time.Instant

object ArticleFixture {
    object Default {
        val createNewArticleRequest = CreateNewArticleRequest(
            title = "title",
            description = "description",
            content = "content",
            tags = null
        )

        val updateArticleRequest =UpdateArticleRequest(
            title = "title",
            description = "description",
            content = "new content"
        )
        private val userProfile = UserProfile(
            username = "jack",
            bio = null,
            image = null,
            following = false
        )

        val updateArticleResponseDto = SingleArticleProfileResponseDto(
            author = userProfile,
            articleId = 1,
            title = "title",
            description = "description",
            content = "new content",
            tags = null,
            favoritesCount = null,
            createdAt = Instant.now(),
            updatedAt = null
        )


        val singleArticleProfileResponseDto = SingleArticleProfileResponseDto(
            author = userProfile,
            articleId = 1,
            title = "title",
            description = "description",
            content = "content",
            tags = null,
            favoritesCount = null,
            createdAt = Instant.now(),
            updatedAt = null
        )
        val singleArticleProfileEntity = SingleArticleProfileEntity(
            author = userProfile,
            articleId = 1,
            title = "title",
            description = "description",
            content = "content",
            tags = null,
            favoritesCount = null,
            createdAt = Instant.now(),
            updatedAt = null
        )

    }
}
