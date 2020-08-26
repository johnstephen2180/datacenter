package com.rpgwikigames.datacenter.service;

import java.util.List;
import java.util.Optional;

import com.rpgwikigames.datacenter.model.Article;

public interface IArticleService {
	List<Article> getAllArticle();

	List<Article> saveAll(List<Article> articleList);

	Article save(Article article);

	Optional<Article> getArticleById(long id);

	void delete(long id);
}
