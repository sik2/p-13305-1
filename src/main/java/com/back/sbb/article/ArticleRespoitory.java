package com.back.sbb.article;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ArticleRespoitory extends JpaRepository<Article, BigInteger> {
}
