package com.tw.yys.backendrealworld.interfaces.inbound

import com.tw.yys.backendrealworld.application.article.ArticleModifyUseCase
import com.tw.yys.backendrealworld.application.article.ArticleQueryUseCase
import com.tw.yys.backendrealworld.interfaces.inbound.dto.CreateNewArticleRequest
import com.tw.yys.backendrealworld.interfaces.inbound.dto.MultipleArticlesResponseDto
import com.tw.yys.backendrealworld.interfaces.inbound.dto.UpdateArticleRequest
import com.tw.yys.backendrealworld.interfaces.outbound.article.response.ArticleResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

    @PostMapping("/articles/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateArticle(
        @PathVariable id: Long,
        @RequestBody request: UpdateArticleRequest
    ): ArticleResponse {
        return articleModifyUseCase.updateArticle(id, request).toResponse()
    }

    @GetMapping("/articles/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findArticleById(
        @PathVariable id: Long,
    ): ArticleResponse {
        return articleQueryUseCase.findArticleById(id).toResponse()
    }

    @DeleteMapping("/articles/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteArticle(
        @PathVariable id: Long,
    ) {
        articleModifyUseCase.deleteArticleById(id)
    }

    @GetMapping("articles")
    @ResponseStatus(HttpStatus.OK)
    fun retrieveArticles(
        @RequestParam(defaultValue = "",required = false) tag: String,
        @RequestParam(defaultValue = "",required = false) author: String,
        @RequestParam(defaultValue = "20", required = false) limit: Int,
        @RequestParam(defaultValue = "0", required = false) offset: Int
    ): MultipleArticlesResponseDto {
        return articleQueryUseCase.retrieveArticles(tag, author, limit, offset)
    }




}
