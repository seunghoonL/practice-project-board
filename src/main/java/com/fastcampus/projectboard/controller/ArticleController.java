package com.fastcampus.projectboard.controller;


import com.fastcampus.projectboard.domain.UserAccount;
import com.fastcampus.projectboard.domain.type.FormStatus;
import com.fastcampus.projectboard.domain.type.SearchType;
import com.fastcampus.projectboard.dto.UserAccountDto;
import com.fastcampus.projectboard.dto.request.ArticleRequest;
import com.fastcampus.projectboard.dto.response.ArticleResponse;
import com.fastcampus.projectboard.dto.response.ArticleWithCommentsResponse;
import com.fastcampus.projectboard.repository.UserAccountRepository;
import com.fastcampus.projectboard.service.ArticleService;
import com.fastcampus.projectboard.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*


 /articles/search-hashtag
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    private final PaginationService paginationService;

    private final UserAccountRepository userAccountRepository;


    @GetMapping
    public String articles(@RequestParam(required = false)SearchType searchType,
                           @RequestParam(required = false) String searchValue,
                           @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                           ModelMap map){
        Page<ArticleResponse> articles = articleService.searchArticles(searchType, searchValue, pageable).map(ArticleResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), articles.getTotalPages());

        map.addAttribute("articles", articles);
        map.addAttribute("paginationBarNumbers", barNumbers);
        map.addAttribute("searchTypes", SearchType.values());
        return "/articles/index";
    }


    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId, Model model){
        ArticleWithCommentsResponse article = ArticleWithCommentsResponse.from(articleService.getArticle(articleId));
        model.addAttribute("article", article);
        model.addAttribute("articleComments", article.articleCommentsResponses());
        return "/articles/detail";
    }


    @GetMapping("/search-hashtag")
    public String searchHashtag(@RequestParam(required = false) String searchValue,
                                @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                                ModelMap map){
        Page<ArticleResponse> articles = articleService.searchArticlesViaHashtag(searchValue, pageable).map(ArticleResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), articles.getTotalPages());
        List<String> hashtags = articleService.getHashtags();

        map.addAttribute("articles", articles);
        map.addAttribute("hashtags", hashtags);
        map.addAttribute("paginationBarNumbers", barNumbers);
        map.addAttribute("searchType", SearchType.HASHTAG);

        return "/articles/search-hashtag";
    }


    @GetMapping("/form")
    public String articleForm(ModelMap map){
        map.addAttribute("formStatus", FormStatus.CREATE);
        return "/articles/form";
    }


    @PostMapping("/form")
    public String postNewArticle(ArticleRequest request, String userId, ModelMap map){
        map.addAttribute("userId", userId);
        UserAccount userAccount = userAccountRepository.findByUserId(userId);

        articleService.saveArticle(request.toDto(UserAccountDto.from(userAccount)));
        return "redirect:/";
    }
}
