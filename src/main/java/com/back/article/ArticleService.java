package com.back.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public void save(String title, String content) {
        Article article = new Article(title, content);
        articleRepository.save(article);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findById(int id) {
        Optional<Article> article = articleRepository.findById(id);
        if(article.isPresent()) {
            return article.get();
        } else {
            throw new RuntimeException("Article not found by id : " + id);
        }
    }
}
