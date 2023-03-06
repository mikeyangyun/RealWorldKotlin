package com.tw.yys.backendrealworld.interfaces.outbound.article

import java.time.Instant

data class ArticleModel(
    val articleId: Long = 0,
    val authorId: String,
    val title: String,
    val description: String,
    val content: String,
    val favoritesCount: Int?,
    val tags: String?,
    val createdAt: Instant?,
    val updatedAt: Instant?
) {
    fun toPo() = ArticlePo(
        authorId = authorId,
        title = title,
        description = description,
        content = content,
        favoritesCount = favoritesCount,
        tags = tags,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
}
