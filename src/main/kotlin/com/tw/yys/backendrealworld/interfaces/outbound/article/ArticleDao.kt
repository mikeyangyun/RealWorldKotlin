package com.tw.yys.backendrealworld.interfaces.outbound.article

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ArticleDao: JpaRepository<ArticlePo, Int> {
    fun findTopById(id: Long): ArticlePo?
    fun deleteArticlePoById(articleId: Long)

    @Query(
        """
            SELECT * FROM articles LIMIT ?1 OFFSET ?2
        """,
        nativeQuery = true
    )
    fun findAllArticlesLimitIsAndOffsetIs(limit: Int, offset: Int): List<ArticlePo>

    @Query(
        """
            SELECT * FROM articles WHERE author_id = ?1 LIMIT ?2 OFFSET ?3
        """,
        nativeQuery = true
    )
    fun findArticleByAuthorIdAndLimitIsAndOffsetIs(
        authorId: String,
        limit: Int,
        offset: Int
    ): List<ArticlePo>

    @Query(
        """
            SELECT * FROM articles WHERE  tag LIKE ?1 AND author_id = ?2 LIMIT ?3 OFFSET ?4
        """,
        nativeQuery = true
    )
    fun findArticleByTagAndAuthorIdAndLimitIsAndOffsetIs(
        tag: String,
        authorId: String,
        limit: Int,
        offset: Int
    ): List<ArticlePo>

    @Query(
        """
            SELECT * FROM articles WHERE  tag LIKE ?1 LIMIT ?2 OFFSET ?3
        """,
        nativeQuery = true
    )
    fun findArticleByTagAndLimitIsAndOffsetIs(
        tag: String,
        limit: Int,
        offset: Int
    ): List<ArticlePo>


}
