package com.rpgwikigames.datacenter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rpgwikigames.datacenter.exception.ResourceNotFoundException;
import com.rpgwikigames.datacenter.model.Article;
import com.rpgwikigames.datacenter.service.ArticleService;
import com.rpgwikigames.datacenter.util.RateGeneratorUtil;

//https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/
@RestController
@RequestMapping("/api")
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	@GetMapping("/apps")
	public List<Article> getAllapps() {
		List<Article> articleList = articleService.getAllArticle();
		articleList.forEach(art -> {
			art.setScoreDetail(RateGeneratorUtil.randomRating(art.getScore()));
		});
		articleService.saveAll(articleList);
		return articleList;
	}

	@PostMapping(value = "/apps", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Article createapp(@Validated @RequestBody Article article) {
		article.setScoreDetail(RateGeneratorUtil.randomRating(article.getScore()));
		return articleService.save(article);
	}

	@GetMapping("/apps/{id}")
	public Article getappById(@PathVariable(value = "id") Long appId) {
		return articleService.getArticleById(appId)
				.orElseThrow(() -> new ResourceNotFoundException("App", "id", appId));
	}

	@PutMapping("/apps/{id}")
	public Article updateNote(@PathVariable(value = "id") Long appId, @Validated @RequestBody Article appDetails) {
		Article article = articleService.getArticleById(appId)
				.orElseThrow(() -> new ResourceNotFoundException("App", "id", appId));

		article.setTitle(appDetails.getTitle());
		article.setContent(appDetails.getContent());
		return articleService.save(article);
	}

	@PatchMapping("/apps/{id}")
	public Article updateNoteAttribute(@PathVariable(value = "id") Long appId, @Validated @RequestBody Article appDetails) {
		Article article = articleService.getArticleById(appId)
				.orElseThrow(() -> new ResourceNotFoundException("App", "id", appId));
		
		article.setTitle(appDetails.getTitle());
		article.setContent(appDetails.getContent());
		return articleService.save(article);
	}

	@DeleteMapping("/apps/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long appId) {
		articleService.getArticleById(appId).orElseThrow(() -> new ResourceNotFoundException("App", "id", appId));
		articleService.delete(appId);
		return ResponseEntity.ok().build();
	}
}
