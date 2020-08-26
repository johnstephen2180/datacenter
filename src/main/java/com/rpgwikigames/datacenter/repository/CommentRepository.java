package com.rpgwikigames.datacenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rpgwikigames.datacenter.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	@Query("Select count(c) From Comment c where article_id = ?1")
	int count(long articleId);
	
	int countByArticleId(long articleId);
}
