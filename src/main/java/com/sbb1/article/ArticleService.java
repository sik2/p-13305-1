package com.sbb1.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    public List<Article> getList() {
        return articleRepository.findAll();
    }

    public void create(String title, String content){

        Article article = Article.builder()
                .title(title)
                .content(content)
                .createdDate(LocalDateTime.now())
                .build();

        articleRepository.save(article);
    }

    public Article getArticle(Integer id){
        return articleRepository.findById(id).get();
    }
}
