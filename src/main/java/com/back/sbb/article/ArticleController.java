package com.back.sbb.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/article")
@RequiredArgsConstructor
@Controller
public class ArticleController {
    private final ArticleService articleService;

    // GET /article/list
    // 게시글 목록 화면
    @GetMapping("/list")
    public String list(Model model) {
        List<Article> articleList = articleService.getList();
        model.addAttribute("articleList", articleList);
        return "article_list";
    }

    // Get /article/detail/{id}
    // 게시글 상세 화면
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable Integer id) {
        Article article = articleService.getArticle(id);
        model.addAttribute("article", article);
        return "article_detail";
    }

    // GET /article/create
    // 게시글 등록 폼 화면
    @GetMapping("/create")
    public String create() {
        return "article_create";
    }

    // POST /article/create
    // 게시글 등록 처리 (폼 제출)
    @PostMapping("/create")
    public String createArticle(@RequestParam String title,@RequestParam String content) {
        articleService.create(title, content);

        return "redirect:/article/list";
    }
}
