package com.back.sbb.article;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleController {

    @GetMapping("/")
    public String root() {
        return "redirect:/article/list";
    }

    @GetMapping("/article/list")
    public String list() {
        return "article/list";
    }
}
