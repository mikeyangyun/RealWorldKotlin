package com.tw.yys.backendrealworld.interfaces.outbound.article

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "articles")
data class ArticlePo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    val id: Long = 0,

    @Column(name = "author_id") val authorId: String,
    @Column(name = "title") var title: String,
    @Column(name = "description") var description: String,
    @Column(name = "content") var content: String,
    @Column(name = "favorites_count") var favoritesCount: Int?,
    @Column(name = "tags") var tags: String?,
    @Column(name = "created_at") val createdAt: Instant?,
    @Column(name = "updated_at") var updatedAt: Instant?
) {
    fun toDomain() = ArticleEntity(
        articleId = id,
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
