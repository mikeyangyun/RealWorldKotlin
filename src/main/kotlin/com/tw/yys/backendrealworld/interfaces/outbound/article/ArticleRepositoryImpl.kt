package com.tw.yys.backendrealworld.interfaces.outbound.article

import com.tw.yys.backendrealworld.domain.ArticleRepository
import org.springframework.stereotype.Component

@Component
class ArticleRepositoryImpl(
    private val dao: ArticleDao
): ArticleRepository {
    override fun save(entity: ArticleEntity): ArticleEntity {
        return dao.save(entity.toPo()).toDomain()
    }

    override fun findArticleById(articleId: Long): ArticleEntity? {
        return dao.findTopById(articleId)?.toDomain()
    }

    override fun deleteArticleById(articleId: Long) {
        dao.deleteArticlePoById(articleId)
    }
}

