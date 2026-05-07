package com.springboottest1.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/article/list")
    @ResponseBody
    public String list() {
        return "article_list";
    }
}
