package com.back.sbb.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("articleList", articleService.getList());
        return "article_list";
    }

    @GetMapping("/create")
    public String create() {
        return "article_create";
    }

    @PostMapping("/create")
    public String create(Article article) {
        articleService.create(article.getTitle(), article.getContent());
        return "redirect:/article/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Optional<Article> op = articleService.detailArticle(id);
        if (op.isPresent()) {
            model.addAttribute("article", op.get());
            return "article_detail";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다.");
        }
    }
}
