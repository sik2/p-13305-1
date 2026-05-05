package com.back.sbb.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    // 전체 게시글 목록 조회
    public List<Article> getList() {
        return articleRepository.findAll();
        // SELECT * FROM article;
    }

    // 특정 게시글 1개 조회 (단건조회)
    public Article getArticle(Integer id) {
        Optional<Article> oa = articleRepository.findById(id);

        if (oa.isPresent()) {
            return oa.get();
        } else {
            throw new RuntimeException("Article not found : " + id);
        }
    }

    // 게시글 저장
    public void create(String title, String content) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setCreatedDate((LocalDateTime.now()));

        articleRepository.save(article);
    }
}
