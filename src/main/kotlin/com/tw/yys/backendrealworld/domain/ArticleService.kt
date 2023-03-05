package com.tw.yys.backendrealworld.domain

import com.tw.yys.backendrealworld.domain.common.errors.ArticleNotFoundException
import com.tw.yys.backendrealworld.domain.common.errors.UserNotFoundException
import com.tw.yys.backendrealworld.domain.common.model.UserProfile
import com.tw.yys.backendrealworld.interfaces.inbound.dto.CreateNewArticleRequest
import com.tw.yys.backendrealworld.interfaces.inbound.dto.UpdateArticleRequest
import com.tw.yys.backendrealworld.interfaces.outbound.article.ArticleEntity
import com.tw.yys.backendrealworld.interfaces.outbound.article.response.SingleArticleProfileEntity
import com.tw.yys.backendrealworld.interfaces.outbound.userInfo.UserInfoEntity
import org.springframework.stereotype.Service

@Service
class ArticleService(
    private val articleRepository: ArticleRepository,
    private val userInfoRepository: UserInfoRepository
) {
    fun createNewArticle(userId: String, command: CreateNewArticleRequest): SingleArticleProfileEntity {
        val existingUser = userInfoRepository.findUserById(userId) ?: throw UserNotFoundException()
        val author = getUserProfile(existingUser)
        val entity = command.toEntity(userId)
        val savedArticle = articleRepository.save(entity)

        return singleArticleProfileEntity(author, savedArticle)
    }

    private fun singleArticleProfileEntity(
        author: UserProfile,
        savedArticle: ArticleEntity
    ) = SingleArticleProfileEntity(
        author = author,
        articleId = savedArticle.articleId,
        title = savedArticle.title,
        description = savedArticle.description,
        content = savedArticle.content,
        favoritesCount = savedArticle.favoritesCount,
        tags = savedArticle.tags,
        createdAt = savedArticle.createdAt,
        updatedAt = savedArticle.updatedAt,
    )

    fun updateArticle(articleId: Long, request: UpdateArticleRequest): SingleArticleProfileEntity {
        val existingArticle = articleRepository.findArticleById(articleId) ?: throw ArticleNotFoundException()

        val existingUser = userInfoRepository.findUserById(existingArticle.authorId) ?: throw UserNotFoundException()
        val author = getUserProfile(existingUser)

        val updated = existingArticle.copy(
            title = request.title,
            description = request.description,
            content = request.content,
        )
        articleRepository.save(updated)

        return singleArticleProfileEntity(author, updated)
    }

    private fun getUserProfile(existingUser: UserInfoEntity): UserProfile {
        val author = UserProfile(
            username = existingUser.username,
            bio = existingUser.bio,
            image = existingUser.image
        )
        return author
    }

    fun findArticleById(articleId: Long): SingleArticleProfileEntity {
        val foundArticle = articleRepository.findArticleById(articleId) ?: throw ArticleNotFoundException()
        val existingUser = userInfoRepository.findUserById(foundArticle.authorId) ?: throw UserNotFoundException()

        val author = getUserProfile(existingUser)

        return singleArticleProfileEntity(author,foundArticle)
    }

    fun deleteArticleById(articleId: Long) {
        articleRepository.deleteArticleById(articleId)
    }

    fun retrieveArticles(tag: String, authorName: String, limit: Int, offset: Int): List<SingleArticleProfileEntity> {
        if (tag.isEmpty() && authorName.isEmpty()) {
            val articleEntityList = articleRepository.findAllArticlesLimitIsAndOffsetIs(limit, offset)
            return articleEntityList.map {
                findArticleById(it.articleId)
            }
        }
        if (tag.isNotEmpty() && authorName.isEmpty()) {
            val articleEntityList = articleRepository.findArticleByTagAndLimitIsAndOffsetIs(tag, limit, offset)
            return articleEntityList.map {
                findArticleById(it.articleId)
            }
        }
        if (tag.isEmpty() && authorName.isNotEmpty()) {
            val user = userInfoRepository.findByUserName(authorName) ?: return emptyList()

            val articleEntityList = articleRepository.findArticleByAuthorIdAndLimitIsAndOffsetIs(user.id, limit, offset)

            return articleEntityList.map {
                findArticleById(it.articleId)
            }
        }
        val user = userInfoRepository.findByUserName(authorName) ?: return emptyList()

        val articleEntityList = articleRepository.findArticleByTagAndAuthorIdAndLimitIsAndOffsetIs(tag, user.id, limit, offset)
        return articleEntityList.map {
            findArticleById(it.articleId)
        }

    }
}

interface ArticleRepository{
    fun save(entity: ArticleEntity): ArticleEntity
    fun findArticleById(articleId: Long): ArticleEntity?
    fun deleteArticleById(articleId: Long)
    fun findAllArticlesLimitIsAndOffsetIs(
        limit: Int,
        offset: Int
    ): List<ArticleEntity>
    fun findArticleByTagAndLimitIsAndOffsetIs(
        tag: String,
        limit: Int,
        offset: Int
    ): List<ArticleEntity>
    fun findArticleByAuthorIdAndLimitIsAndOffsetIs(
        authorId: String,
        limit: Int,
        offset: Int
    ): List<ArticleEntity>
    fun findArticleByTagAndAuthorIdAndLimitIsAndOffsetIs(
        tag: String,
        authorId: String,
        limit: Int,
        offset: Int
    ): List<ArticleEntity>
}
