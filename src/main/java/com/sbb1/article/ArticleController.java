package com.sbb1.article;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("articleList", articleService.getList());
        return "article/article_list";
    }

    @GetMapping("/create")
    public String create(ArticleForm articleForm) {
        return "article/article_form";
    }

    //    @PostMapping("/create")
//    public String create(String title, String content){
//        articleService.create(title,content);
//        return "redirect:/article/list";
//    }
    @PostMapping("/create")
    public String create(@Valid ArticleForm articleForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "article/article_form";
        }

        articleService.create(
                articleForm.getTitle(),
                articleForm.getContent()
        );

        return "redirect:/article/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Article article = articleService.getArticle(id);
        model.addAttribute("article", article);

        return "article/article_detail";
    }

}
