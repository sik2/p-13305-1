package com.article_mission.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {
    @GetMapping("/")
    public String list() {
        return "redirect:/article/list";
    }
}
