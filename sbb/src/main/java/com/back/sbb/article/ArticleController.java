package com.back.sbb.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/")
    public String root() {
        return "redirect:/article/list";
    }

    @GetMapping("/article/list")
    public String list(Model model) {
        model.addAttribute("articleList", articleService.getList());
        return "article/list";
    }

    @GetMapping("/article/create")
    public String createForm() {
        return "article/create";
    }

    @PostMapping("/article/create")
    public String create(@RequestParam String title,
                         @RequestParam String content) {
        articleService.create(title, content);
        return "redirect:/article/list";
    }

    @GetMapping("/article/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.get(id));
        return "article/detail";
    }
}