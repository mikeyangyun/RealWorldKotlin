package com.tw.yys.backendrealworld.interfaces.inbound.dto

data class MultipleArticlesResponseDto(
    val articles: List<SingleArticleProfileResponseDto>,
    val articlesCount: Int
)
