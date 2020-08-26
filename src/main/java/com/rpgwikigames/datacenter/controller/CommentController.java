package com.rpgwikigames.datacenter.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rpgwikigames.datacenter.exception.ResourceNotFoundException;
import com.rpgwikigames.datacenter.model.Article;
import com.rpgwikigames.datacenter.model.Comment;
import com.rpgwikigames.datacenter.repository.CommentRepository;
import com.rpgwikigames.datacenter.service.ArticleService;

@RestController
@RequestMapping("/api")
public class CommentController {
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private ArticleService articleService;

	@GetMapping("/comments")
	public List<Comment> getAllapps() {
		return commentRepository.findAll(Sort.by(Sort.Direction.DESC, "createTime"));
	}

	@PostMapping("/posts/{appId}/comment")
	public Comment createComment(@PathVariable(value = "appId") Long appId, @Valid @RequestBody Comment comment) {
		return articleService.getArticleById(appId).map(post -> {
			comment.setArticle(post);
			return commentRepository.save(comment);
		}).orElseThrow(() -> new ResourceNotFoundException("App", "id", appId));
	}

	@PostMapping("/posts/{appId}/comments")
	public List<Comment> createCommentList(@PathVariable(value = "appId") Long appId,
			@Valid @RequestBody List<Comment> comments) {
		Optional<Article> articleOpt = articleService.getArticleById(appId);
		return articleOpt.map(post -> {
			comments.stream().forEach(cm -> cm.setArticle(post));
			Article article = articleOpt.get();
			article.setAllReviewsCount(comments.size());
			articleService.save(article);

			return commentRepository.saveAll(comments);
		}).orElseThrow(() -> new ResourceNotFoundException("App", "id", appId));

	}
}
