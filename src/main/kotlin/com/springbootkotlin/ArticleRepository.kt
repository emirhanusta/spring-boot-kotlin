package com.springbootkotlin

import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository: JpaRepository<Article, Long> {
    fun findByTitle(title: String): Article?
}