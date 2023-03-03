package com.tw.yys.backendrealworld.interfaces.outbound.comment

import java.math.BigInteger
import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "comments")
data class CommentPo(
    @Id val id: BigInteger,

    @Column(name = "author_id") val authorId: String,
    @Column(name = "article_id") var articleId: String,
    @Column(name = "content") var content: String,
    @Column(name = "created_at") val createdAt: Instant?,
    @Column(name = "updated_at") var updatedAt: Instant?
)
