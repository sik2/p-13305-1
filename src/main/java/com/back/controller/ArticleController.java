package com.back.controller;

import com.back.entity.Article;
import com.back.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("articles", articleService.findAll());
        return "article/list";
    }

    @GetMapping("/create")
    public String createForm() {
        return "article/create";
    }

    @PostMapping("/create")
    public String create(@RequestParam String title,
                         @RequestParam String content) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        articleService.save(article);
        return "redirect:/article/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.findById(id));
        return "article/detail";
    }
}