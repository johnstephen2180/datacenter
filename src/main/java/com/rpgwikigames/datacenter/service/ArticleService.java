package com.rpgwikigames.datacenter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rpgwikigames.datacenter.model.Article;
import com.rpgwikigames.datacenter.repository.ArticleRepository;

@Service
public class ArticleService implements IArticleService {

	@Autowired
	private ArticleRepository articleRepo;

	@Override
	public List<Article> getAllArticle() {
		return articleRepo.findAll(Sort.by(Sort.Direction.DESC, "updated"));
	}

	@Override
	public List<Article> saveAll(List<Article> articleList) {
		return articleRepo.saveAll(articleList);
	}

	@Override
	public Article save(Article article) {
		return articleRepo.save(article);
	}

	@Override
	public Optional<Article> getArticleById(long id) {
		return articleRepo.findById(id);
	}

	@Override
	public void delete(long id) {
		articleRepo.deleteById(id);
	}

}
