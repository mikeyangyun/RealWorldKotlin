package com.tw.yys.backendrealworld.interfaces.outbound.article

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleDao: JpaRepository<ArticlePo, Int> {

}
