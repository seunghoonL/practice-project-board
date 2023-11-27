package com.fastcampus.projectboard.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/*


 /articles/search-hashtag
 */
@Controller
@RequestMapping("/articles")
public class ArticleController {



    @GetMapping
    public String articles(Model model){
        model.addAttribute("articles", List.of());
        return "/articles/index";
    }
}