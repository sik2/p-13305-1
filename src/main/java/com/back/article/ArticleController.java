package com.back.article;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Controller
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/list")
    public String list() {
        return "article_list";
    }

    @GetMapping("/create")
    public String create() {
        return "article_create";
    }

    @PostMapping("/create")
    public String createArticle(@RequestParam String title, @RequestParam String content) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setCreateDate(LocalDateTime.now());
        articleService.save(article);

        return "article_create";
    }
}
