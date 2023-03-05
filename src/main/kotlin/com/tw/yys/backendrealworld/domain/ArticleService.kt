package com.tw.yys.backendrealworld.domain

import org.springframework.stereotype.Service

@Service
class ArticleService(
    private val repository: ArticleRepository
) {
}

interface ArticleRepository{

}
