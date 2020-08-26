package com.rpgwikigames.datacenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rpgwikigames.datacenter.model.Article;
//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

}
