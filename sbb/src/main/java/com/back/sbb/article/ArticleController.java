package com.back.sbb.article;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    public String list(Model model) {
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);

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

    @GetMapping("/article/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Article article = articleRepository.findById(id).orElseThrow();
        model.addAttribute("article", article);

        return "article/detail";
    }
}
