package com.tw.yys.backendrealworld.interfaces.outbound.article.response

import com.tw.yys.backendrealworld.domain.common.model.UserProfile
import com.tw.yys.backendrealworld.interfaces.inbound.dto.SingleArticleProfileResponseDto
import java.time.Instant

data class SingleArticleProfileEntity(
    val author: UserProfile,
    val title: String,
    val description: String,
    val content: String,
    val favoritesCount: Int?,
    val tags: String?,
    val createdAt: Instant?,
    val updatedAt: Instant?
) {
    fun toDto() = SingleArticleProfileResponseDto(
        author = author,
        title = title,
        description = description,
        content = content,
        favoritesCount = favoritesCount,
        tags = tags,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
