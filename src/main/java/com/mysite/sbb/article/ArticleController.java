package com.mysite.sbb.article;

import com.mysite.sbb.user.User;
import com.mysite.sbb.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Article> articleList = articleService.getList();
        model.addAttribute("articleList", articleList);
        return "article_list";
    }

    @GetMapping("/create")
    public String create(ArticleForm articleForm) {
        return "article_form";
    }

    @PostMapping("/create")
    public String createArticle(@Valid ArticleForm articleForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "article_form";
        }

        User author = userService.getUser(principal.getName());
        articleService.create(articleForm.getTitle(), articleForm.getContent(), author);
        return "redirect:/article/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable Integer id) {
        Article article = articleService.getArticle(id);
        model.addAttribute("article", article);
        return "article_detail";
    }

    @GetMapping("/modify/{id}")
    public String modifyForm(Model model, @PathVariable Integer id, ArticleForm articleForm, Principal principal) {
        Article article = articleService.getArticle(id);
        articleService.validateModifyPermission(article, principal.getName());
        articleForm.setTitle(article.getTitle());
        articleForm.setContent(article.getContent());
        model.addAttribute("articleId", id);
        return "article_form";
    }

    @PostMapping("/modify/{id}")
    public String modifyArticle(@Valid ArticleForm articleForm, BindingResult bindingResult,
                                @PathVariable Integer id, Model model, Principal principal) {
        Article article = articleService.getArticle(id);
        articleService.validateModifyPermission(article, principal.getName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("articleId", id);
            return "article_form";
        }

        articleService.modify(article, articleForm.getTitle(), articleForm.getContent());
        return "redirect:/article/detail/" + id;
    }

    @PostMapping("/delete/{id}")
    public String deleteArticle(@PathVariable Integer id, Principal principal) {
        Article article = articleService.getArticle(id);
        articleService.validateDeletePermission(article, principal.getName());
        articleService.delete(article);
        return "redirect:/article/list";
    }
}
