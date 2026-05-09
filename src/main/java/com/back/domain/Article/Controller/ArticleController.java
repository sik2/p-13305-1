package com.back.domain.Article.Controller;

import com.back.domain.Article.entity.Article;
import com.back.domain.Article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/list")
    public String list(Model model){
        List<Article> articleList = articleService.getList();
        model.addAttribute("articleList", articleList);
        return "list.html";
    }

    @GetMapping("/create")
    public String create(){
        return "write.html";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id){
        Article article = articleService.getArticleById(id);
        if(article == null){
            // 에러 메세지 띄우기
            return "redirect:/article/list";
        }
        model.addAttribute("article", article);
        return "detail.html";
    }

    @PostMapping("/create")
    public String create(@RequestParam String title, @RequestParam String content) {
        articleService.write(title, content);
        return "redirect:/article/list";
    }
}
