package com.back.sbb.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public void create(String title, String content) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setCreateDate(LocalDateTime.now());
        articleRepository.save(article);
    }

    public List<Article> getList() {
        return articleRepository.findAll();
    }

    public Optional<Article> detailArticle(Integer id) {
        return articleRepository.findById(id);
    }
}
