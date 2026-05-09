package com.springboottest1.article;

import com.springboottest1.article.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    AricleRepository aricleRepository;

    public void save(String title, String content){
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        aricleRepository.save(article);
    }

    public List<Article> getList() {
        return aricleRepository.findAll();
    }

    public Article getArticle(int id) {
        Optional<Article> article = aricleRepository.findById(id);
        if (article.isPresent()) {
            return article.get();
        } else {
            throw new DataNotFoundException("Article not found with id: " + id);
        }
    }
}
