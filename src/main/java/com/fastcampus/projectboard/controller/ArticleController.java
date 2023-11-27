package com.fastcampus.projectboard.controller;


import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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


    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId, Model model){
        model.addAttribute("article","articleData");  // TODO: 2023-11-27 모델 값 수정 필요  
        model.addAttribute("articleComments", List.of());
        return "/articles/detail";
    }
}
