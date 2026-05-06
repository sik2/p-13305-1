package com.example.board.controller;

import com.example.board.entity.Article;
import com.example.board.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    // 루트 URL → 게시글 리스트로 리다이렉트
    @GetMapping("/")
    public String index() {
        return "redirect:/article/list";
    }

    // 게시글 리스트 페이지
    @GetMapping("/article/list")
    public String list(Model model) {
        List<Article> articles = articleService.findAll();
        model.addAttribute("articles", articles);
        return "article/list";
    }

    // 게시글 등록 폼 페이지
    @GetMapping("/article/create")
    public String createForm(Model model) {
        model.addAttribute("article", new Article());
        return "article/create";
    }

    // 게시글 등록 처리 (POST)
    @PostMapping("/article/create")
    public String create(@RequestParam String title,
                         @RequestParam String content,
                         @RequestParam String author) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setAuthor(author);
        articleService.save(article);
        return "redirect:/article/list";
    }

    // 게시글 상세 페이지
    @GetMapping("/article/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Article article = articleService.findById(id);
        model.addAttribute("article", article);
        return "article/detail";
    }
}
