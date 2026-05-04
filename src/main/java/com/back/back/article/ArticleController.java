package com.back.back.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ArticleController {
	private final ArticleService articleService;

	@GetMapping("/")
	public String root() {
		return "redirect:/article/list";
	}

	@GetMapping("/article/list")
	public String list(Model model) {
		model.addAttribute("articles", articleService.findAll());
		return "article/list";
	}
}
