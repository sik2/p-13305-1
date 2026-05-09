package com.springboottest1.article;

import com.springboottest1.article.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Article> articles = articleService.getList();
        model.addAttribute("articles", articles);
        return "article_list";
    }

    @GetMapping("/create")
    public String create() {
        return "article_create";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") int id) {
        Article article = articleService.getArticle(id);
        model.addAttribute("article", article);
        return "article_detail";
    }

    @PostMapping("/create")
    public String create(@RequestParam(value = "title") String title,@RequestParam(value = "content") String content) {
        articleService.save(title, content);
        return "redirect:/article/list";
    }
}
