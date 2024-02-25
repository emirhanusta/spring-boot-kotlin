package com.springbootkotlin.controller

import com.springbootkotlin.dto.ArticleDto
import com.springbootkotlin.dto.ArticleRequest
import com.springbootkotlin.service.ArticleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/articles")
class ArticleController(val articleService: ArticleService) {

    @GetMapping("/getAll")
    fun getAllArticles(): ResponseEntity<MutableList<ArticleDto>> = ResponseEntity.ok(articleService.getAllArticles())

    @GetMapping("/getByTitle/{title}")
    fun getArticleByTitle(@PathVariable title: String): ResponseEntity<ArticleDto> = ResponseEntity.ok(articleService.getArticleByTitle(title))

    @PostMapping("/save")
    fun saveArticle(@RequestBody article: ArticleRequest): ResponseEntity<ArticleDto> = ResponseEntity.ok(articleService.saveArticle(article))

    @PutMapping("/update/{id}")
    fun updateArticle(@PathVariable id: Long, @RequestBody article: ArticleRequest): ResponseEntity<ArticleDto> = ResponseEntity.ok(articleService.updateArticle(id, article))

    @DeleteMapping("/delete/{id}")
    fun deleteArticle(@PathVariable id:Long) = articleService.deleteArticle(id)
}
