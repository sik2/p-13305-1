package com.article_mission.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;


    // 메인화면, Article 목록 조회 메서드
    @GetMapping("/list")
    public String list(Model model) {
        List<Article> articles =  articleService.getArticles();
        model.addAttribute("articles", articles);
        return "article_list";
    }

    // 등록 화면 출력
    @GetMapping("/create")
    public String createForm() {
        return "article_create";
    }

    // 등록 메서드
    @PostMapping("/insert")
    public String insertArticle(@RequestParam String title, @RequestParam String content) {

        Article article = new Article(title, content);

        articleService.insert(article);

        return "redirect:/article/list";
    }

    // 상세 페이지 메서드
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable long id, Model model) {
        Article article = articleService.getArticle(id);

        model.addAttribute("article", article);

        return "article_detail";
    }


    // 삭제 메서드
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        articleService.delete(id);
        return "redirect:/article/list";
    }

    // 수정 화면 출력 메서드
    @GetMapping("/modify/{id}")
    public String modify(@PathVariable long id,Model model) {
        Article article = articleService.getArticle(id);

        model.addAttribute("article", article);

        return "article_modify";
    }

    // 수정 처리 메서드
    @PostMapping("/update/{id}")
    public String updateArticle(@PathVariable long id, @RequestParam String title, @RequestParam String content) {
        articleService.update(id, title, content);
        return "redirect:/article/detail/%d".formatted(id);
    }

}
