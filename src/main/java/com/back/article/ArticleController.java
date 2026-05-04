package com.back.article;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/article/list")
    public String list(Model model) {
        List<Article> articleList = articleService.findAll();
        model.addAttribute(articleList);
        return "article_list";
    }

    @GetMapping("/article/create")
    public String create(ArticleForm articleForm) {
        return "article_create";
    }

    @GetMapping("article/detail/{id}")
    public String detail(Model model, @PathVariable("id") int id){
        Article article = articleService.findById(id);
        model.addAttribute(article);
        return "article_detail";
    }

    @PostMapping("/article/create")
    public String createArticle(@Valid ArticleForm articleForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
           return "article_create";
        }
        articleService.save(articleForm.getTitle(), articleForm.getContent());
        return "redirect:/article/list";
    }
}
