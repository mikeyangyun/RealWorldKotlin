package com.tw.yys.backendrealworld.interfaces.inbound

import com.tw.yys.backendrealworld.application.article.ArticleModifyUseCase
import com.tw.yys.backendrealworld.application.article.ArticleQueryUseCase
import org.springframework.web.bind.annotation.RestController

@RestController
class ArticleController(
    private val articleQueryUseCase: ArticleQueryUseCase,
    private val articleModifyUseCase: ArticleModifyUseCase
) {

}
