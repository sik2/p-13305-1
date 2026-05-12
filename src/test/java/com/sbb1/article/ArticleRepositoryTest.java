package com.sbb1.article;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    void testJpa(){
        Article article = new Article();

        article.setTitle("제목");
        article.setContent("내용");
        article.setCreatedDate(LocalDateTime.now());

        articleRepository.save(article);
    }

    @Test
    @DisplayName("getList")
    void t1(){
        List<Article> articles = articleRepository.findAll();
        assertEquals(1,articles.size());
        assertEquals("제목", articles.get(0).getTitle());
    }


    @Test
    @DisplayName("createArticle")
    void t2(){

    }

}
