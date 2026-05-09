package com.back.domain.Article.service;

import com.back.domain.Article.entity.Article;
import com.back.domain.Article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> getList() {
        return articleRepository.findAll();
    }

    public void write(String title, String content) {
        Article article = new Article(title, content);
        article.setCreatedDate(now());
        articleRepository.save(article);
    }

    public Article getArticleById(int id) {
        Optional<Article> article = articleRepository.findById(id);
        if(article.isPresent()) {
            return article.get();
        }
        else{
            return null;
        }
    }
}
