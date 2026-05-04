package com.article_mission.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public void insert(Article article) {
        articleRepository.save(article);
    }

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    public Article getArticle(long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디를 찾을 수 없습니다."));
    }

    public void delete(long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디를 찾을 수 없습니다."));
        articleRepository.delete(article);
    }

    public void update(long id, String title, String content) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디를 찾을 수 없습니다."));

        article.setTitle(title);
        article.setContent(content);

        articleRepository.save(article);

    }
}
