package com.tw.yys.backendrealworld.application.article

import com.tw.yys.backendrealworld.domain.ArticleService
import com.tw.yys.backendrealworld.interfaces.inbound.dto.SingleArticleProfileResponseDto
import org.springframework.stereotype.Service

@Service
class ArticleQueryUseCase(
    private val service: ArticleService
) {
    fun findArticleById(articleId: Long): SingleArticleProfileResponseDto {
        return service.findArticleById(articleId).toDto()
    }
}
