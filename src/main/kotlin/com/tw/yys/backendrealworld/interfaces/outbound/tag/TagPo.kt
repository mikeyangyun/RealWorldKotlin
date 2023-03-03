package com.tw.yys.backendrealworld.interfaces.outbound.tag

import java.math.BigInteger
import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "tags")
data class TagPo(
    @Id val id: BigInteger,

    @Column(name = "article_id") var articleId: String,
    @Column(name = "tag") var tag: String,
    @Column(name = "created_at") val createdAt: Instant?,
)
