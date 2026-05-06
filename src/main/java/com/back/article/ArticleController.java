package com.back.article;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Article> articleList = articleService.findAll();
        model.addAttribute(articleList);
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

        return "redirect:/article/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable int id){
        Article article = articleService.findById(id);
        model.addAttribute(article);
        return "article_detail";
    }
}
