package com.tw.yys.backendrealworld.application.article

import com.tw.yys.backendrealworld.domain.ArticleService
import com.tw.yys.backendrealworld.interfaces.inbound.dto.MultipleArticlesResponseDto
import com.tw.yys.backendrealworld.interfaces.inbound.dto.SingleArticleProfileResponseDto
import org.springframework.stereotype.Service

@Service
class ArticleQueryUseCase(
    private val service: ArticleService
) {
    fun findArticleById(articleId: Long): SingleArticleProfileResponseDto {
        return service.findArticleById(articleId).toDto()
    }

    fun retrieveArticles(tag: String, author: String, limit: Int, offset: Int): MultipleArticlesResponseDto {
        val tagValue = when (tag.isNotEmpty()) {
            true -> "%$tag%"
            else -> tag
        }
        val articleProfileEntities = service.retrieveArticles(tagValue, author, limit, offset)

        val articleList = articleProfileEntities.map { it.toDto() }

        return MultipleArticlesResponseDto(
            articleList,
            articleList.count()
        )
    }
}
