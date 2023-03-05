package com.tw.yys.backendrealworld.interfaces.inbound.dto

data class UpdateArticleRequest(
    val title: String,
    val description: String,
    val content: String
)
