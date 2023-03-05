package com.tw.yys.backendrealworld.interfaces.outbound.article

import java.math.BigInteger
import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "articles")
data class ArticlePo(
    @Id val id: BigInteger,

    @Column(name = "author_id") val authorId: String,
    @Column(name = "title") var title: String,
    @Column(name = "description") var description: String,
    @Column(name = "content") var content: String,
    @Column(name = "favorites_count") var favoritesCount: Integer?,
    @Column(name = "tags") var tags: String?,
    @Column(name = "created_at") val createdAt: Instant?,
    @Column(name = "updated_at") var updatedAt: Instant?
)
