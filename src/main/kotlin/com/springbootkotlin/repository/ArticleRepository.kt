package com.springbootkotlin.repository

import com.springbootkotlin.model.Article
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository: JpaRepository<Article, Long> {
    fun findByTitle(title: String): Article?
}