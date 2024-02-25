package com.springbootkotlin.dto

import com.springbootkotlin.model.Article

data class ArticleDto(
    val id: Long,
    val title: String,
    val content: String
) {
    fun toArticle(article: Article): ArticleDto {
        return ArticleDto(article.id!!, article.title, article.content)
    }
}
