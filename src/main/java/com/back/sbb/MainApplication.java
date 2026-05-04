package com.back.sbb;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainApplication {

    @GetMapping("/")
    public String index(){

        return "redirect:/article/list";
    }
}
