package com.back.sbb.home;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class Home {

    @GetMapping("/")
    public String home(){
        return "redirect:/article/list";
    }
}
