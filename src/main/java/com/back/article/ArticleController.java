package com.back.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @GetMapping("/list")
    public String list() {
        return "article_list";
    }

    @GetMapping("/create")
    public String create() {
        return "article_create";
    }
}
