package com.mysite.sbb.article;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> getList() {
        return articleRepository.findAll();
    }

    public Article getArticle(Integer id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("article not found"));
    }

    public void create(String title, String content, User author) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setAuthor(author);
        article.setCreatedDate(LocalDateTime.now());
        articleRepository.save(article);
    }

    public void validateModifyPermission(Article article, String username) {
        if (article.getAuthor() == null || !article.getAuthor().getUsername().equals(username)) {
            throw new ResponseStatusException(FORBIDDEN, "본인 글만 수정할 수 있습니다.");
        }
    }

    public void validateDeletePermission(Article article, String username) {
        if (article.getAuthor() == null || !article.getAuthor().getUsername().equals(username)) {
            throw new ResponseStatusException(FORBIDDEN, "본인 글만 삭제할 수 있습니다.");
        }
    }

    public void modify(Article article, String title, String content) {
        article.setTitle(title);
        article.setContent(content);
        articleRepository.save(article);
    }

    public void delete(Article article) {
        articleRepository.delete(article);
    }
}
