package com.tw.yys.backendrealworld.application.article

import com.tw.yys.backendrealworld.domain.ArticleService
import com.tw.yys.backendrealworld.interfaces.inbound.dto.CreateNewArticleRequest
import com.tw.yys.backendrealworld.interfaces.inbound.dto.SingleArticleProfileResponseDto
import com.tw.yys.backendrealworld.interfaces.inbound.dto.UpdateArticleRequest
import org.springframework.stereotype.Service

@Service
class ArticleModifyUseCase(
    private val service: ArticleService
) {
    fun createNewArticle(userId: String, command: CreateNewArticleRequest): SingleArticleProfileResponseDto {
        return service.createNewArticle(userId, command).toDto()
    }

    fun updateArticle(articleId: Long, request: UpdateArticleRequest): SingleArticleProfileResponseDto {
        return service.updateArticle(articleId, request)
    }

}
