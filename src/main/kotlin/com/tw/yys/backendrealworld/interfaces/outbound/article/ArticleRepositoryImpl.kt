package com.tw.yys.backendrealworld.interfaces.outbound.article

import com.tw.yys.backendrealworld.domain.ArticleRepository
import org.springframework.stereotype.Component

@Component
class ArticleRepositoryImpl(
    private val dao: ArticleDao
): ArticleRepository {
    override fun save(entity: ArticleModel): ArticleModel {
        return dao.save(entity.toPo()).toDomain()
    }

    override fun findArticleById(articleId: Long): ArticleModel? {
        return dao.findTopById(articleId)?.toDomain()
    }

    override fun deleteArticleById(articleId: Long) {
        dao.deleteArticlePoById(articleId)
    }

    override fun findAllArticlesLimitIsAndOffsetIs(
        limit: Int,
        offset: Int
    ): List<ArticleModel> {
        return dao.findAllArticlesLimitIsAndOffsetIs(limit, offset).map { it.toDomain() }
    }

    override fun findAllArticlesByAuthorIdAndLimitIsAndOffsetIs(
        authorId: String,
        limit: Int,
        offset: Int
    ): List<ArticleModel> {
        return dao.findArticleByAuthorIdAndLimitIsAndOffsetIs(authorId, limit, offset).map { it.toDomain() }
    }

    override fun findAllArticlesByTagAndAuthorIdAndLimitIsAndOffsetIs(
        tag: String,
        authorId: String,
        limit: Int,
        offset: Int
    ): List<ArticleModel> {
        return dao.findArticleByTagAndAuthorIdAndLimitIsAndOffsetIs(tag, authorId, limit, offset).map { it.toDomain() }
    }

    override fun findAllArticlesByTagAndLimitIsAndOffsetIs(
        tag: String,
        limit: Int,
        offset: Int
    ): List<ArticleModel> {
        return dao.findArticleByTagAndLimitIsAndOffsetIs(tag, limit, offset).map { it.toDomain() }
    }
}

