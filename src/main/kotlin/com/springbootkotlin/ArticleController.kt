package com.springbootkotlin

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/articles")
class ArticleController(val articleRepository: ArticleRepository) {

    @GetMapping("/getAll")
    fun getAllArticles(): MutableList<Article> = articleRepository.findAll()

    @GetMapping("/getByTitle/{title}")
    fun getArticleByTitle(@PathVariable title: String) = articleRepository.findByTitle(title) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found")

    @PostMapping("/save")
    fun saveArticle(@RequestBody article: Article): Article {
        articleRepository.save(article)
        return article
    }

    @PutMapping("/update/{id}")
    fun updateArticle(@PathVariable id: Long, @RequestBody article: Article): Article {
        val existingArticle = articleRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found") }
        existingArticle.title = article.title
        existingArticle.content = article.content
        articleRepository.save(article)
        return article
    }

    @DeleteMapping("/delete/{id}")
    fun deleteArticle(@PathVariable id:Long) {
        val existingArticle = articleRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found") }
        articleRepository.delete(existingArticle)
    }
}
