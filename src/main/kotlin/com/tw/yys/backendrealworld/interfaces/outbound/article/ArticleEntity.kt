package com.tw.yys.backendrealworld.interfaces.outbound.article

import java.math.BigInteger
import java.time.Instant

data class ArticleEntity(
    val id: BigInteger,
    val authorId: String,
    val title: String,
    val description: String,
    val content: String,
    val favoritesCount: Integer?,
    val tags: String?,
    val createdAt: Instant?,
    val updatedAt: Instant?
) {
}
