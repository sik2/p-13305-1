package com.example.p_13305_1.article;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article,Integer> {

    Optional<Article> findByTitle(String title);
}
