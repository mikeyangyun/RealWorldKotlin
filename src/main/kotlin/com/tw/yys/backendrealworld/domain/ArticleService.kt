package com.tw.yys.backendrealworld.domain

import com.tw.yys.backendrealworld.domain.common.errors.UserNotFoundException
import com.tw.yys.backendrealworld.domain.common.model.UserProfile
import com.tw.yys.backendrealworld.interfaces.inbound.dto.CreateNewArticleRequest
import com.tw.yys.backendrealworld.interfaces.outbound.article.ArticleEntity
import com.tw.yys.backendrealworld.interfaces.outbound.article.response.SingleArticleProfileEntity
import org.springframework.stereotype.Service

@Service
class ArticleService(
    private val repository: ArticleRepository,
    private val userInfoService: UserInfoService
) {
    fun createNewArticle(userId: String, command: CreateNewArticleRequest): SingleArticleProfileEntity {
        val existingUser = userInfoService.findUserById(userId) ?: throw UserNotFoundException()
        val author = UserProfile(
            username = existingUser.username,
            bio = existingUser.bio,
            image = existingUser.image
        )
        val entity = command.toEntity(userId)
        val savedArticle = repository.save(entity)

        return SingleArticleProfileEntity(
            author = author,
            title = savedArticle.title,
            description = savedArticle.description,
            content = savedArticle.content,
            favoritesCount = savedArticle.favoritesCount,
            tags = savedArticle.tags,
            createdAt = savedArticle.createdAt,
            updatedAt = savedArticle.updatedAt
        )
    }
}

interface ArticleRepository{
    fun save(entity: ArticleEntity): ArticleEntity

}
