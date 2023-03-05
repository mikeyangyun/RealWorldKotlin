package com.tw.yys.backendrealworld.interfaces.inbound.dto

import com.tw.yys.backendrealworld.domain.common.model.UserProfile
import com.tw.yys.backendrealworld.interfaces.outbound.article.response.ArticleResponse
import java.time.Instant

data class SingleArticleProfileResponseDto(
    val articleId : Long,
    val author: UserProfile,
    val title: String,
    val description: String,
    val content: String,
    val favoritesCount: Int?,
    val tags: String?,
    val createdAt: Instant?,
    val updatedAt: Instant?
) {
    fun toResponse() = ArticleResponse(this)
}
