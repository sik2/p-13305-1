package com.back.sbb.article;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/article/list";
    }

    @GetMapping("/article/list")
    public String list() {
        return "article/list";
    }

    @GetMapping("/article/create")
    public String create() {
        return "article/create";
    }

    @PostMapping("/article/create")
    public String create(@RequestParam String title, @RequestParam String content) {
        Article article = new Article(title, content);
        articleRepository.save(article);

        return "redirect:/article/list";
    }
}
