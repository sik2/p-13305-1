package com.springboottest1.article;

import com.springboottest1.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AricleRepository extends JpaRepository<Article, Integer> {
}
