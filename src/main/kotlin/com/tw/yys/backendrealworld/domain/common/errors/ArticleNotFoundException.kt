package com.tw.yys.backendrealworld.domain.common.errors

class ArticleNotFoundException: RealWorldException(
    message = "This article is not found."
)
