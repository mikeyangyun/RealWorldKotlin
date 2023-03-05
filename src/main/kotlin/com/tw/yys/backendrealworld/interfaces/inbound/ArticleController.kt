package com.tw.yys.backendrealworld.interfaces.inbound

import com.tw.yys.backendrealworld.application.article.ArticleModifyUseCase
import com.tw.yys.backendrealworld.application.article.ArticleQueryUseCase
import com.tw.yys.backendrealworld.interfaces.inbound.dto.CreateNewArticleRequest
import com.tw.yys.backendrealworld.interfaces.outbound.article.response.ArticleResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class ArticleController(
    private val articleQueryUseCase: ArticleQueryUseCase,
    private val articleModifyUseCase: ArticleModifyUseCase
) {
    @PostMapping("/articles")
    @ResponseStatus(HttpStatus.OK)
    fun createNewArticle(
        @RequestParam userId: String,
        @RequestBody request: CreateNewArticleRequest
    ): ArticleResponse {
        return articleModifyUseCase.createNewArticle(userId, request).toResponse()
    }

}
