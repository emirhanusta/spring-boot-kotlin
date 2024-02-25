package com.springbootkotlin.service

import com.springbootkotlin.dto.ArticleDto
import com.springbootkotlin.dto.ArticleRequest
import com.springbootkotlin.model.Article
import com.springbootkotlin.repository.ArticleRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ArticleService(val articleRepository: ArticleRepository) {

    fun getAllArticles(): MutableList<ArticleDto> = articleRepository.findAll().map {
        ArticleDto(it.id!!, it.title, it.content)
    }.toMutableList()

    fun getArticleByTitle(title: String) = articleRepository.findByTitle(title)?.let {
        ArticleDto(it.id!!, it.title, it.content)
    } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found")

    fun saveArticle(article: ArticleRequest): ArticleDto {
        val savedArticle = articleRepository.save(Article(title = article.title, content = article.content))
        return ArticleDto(savedArticle.id!!, savedArticle.title, savedArticle.content)
    }

    fun updateArticle(id: Long, article: ArticleRequest): ArticleDto {
        val existingArticle = articleRepository.findById(id).orElseThrow { throw Exception("Article not found") }
        existingArticle.title = article.title
        existingArticle.content = article.content
        return ArticleDto(id, existingArticle.title, existingArticle.content)
            .toArticle(articleRepository.save(existingArticle))
    }

    fun deleteArticle(id: Long) {
        val existingArticle = articleRepository.findById(id).orElseThrow { throw Exception("Article not found") }
        articleRepository.delete(existingArticle)
    }
}