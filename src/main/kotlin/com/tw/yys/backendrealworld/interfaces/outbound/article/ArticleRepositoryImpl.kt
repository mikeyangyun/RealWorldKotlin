package com.tw.yys.backendrealworld.interfaces.outbound.article

import com.tw.yys.backendrealworld.domain.ArticleRepository
import org.springframework.stereotype.Component

@Component
class ArticleRepositoryImpl(
    private val dao: ArticleDao
): ArticleRepository {

}
