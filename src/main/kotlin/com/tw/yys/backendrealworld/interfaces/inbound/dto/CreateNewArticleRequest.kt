package com.tw.yys.backendrealworld.interfaces.inbound.dto

import com.tw.yys.backendrealworld.interfaces.outbound.article.ArticleEntity
import java.time.Instant


data class CreateNewArticleRequest(
    val title: String,
    val description: String,
    val content: String,
    val tags: List<String>?,
){
    fun toEntity(authorId: String) = ArticleEntity(
        authorId = authorId,
        title = title,
        description = description,
        content = content,
        tags = tags?.joinToString(","),
        favoritesCount = 0,
        createdAt = Instant.now(),
        updatedAt = null
    )
}
