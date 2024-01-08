package com.fastcampus.projectboard.controller;

import com.fastcampus.projectboard.config.SecurityConfig;
import com.fastcampus.projectboard.dto.ArticleWithCommentsDto;
import com.fastcampus.projectboard.dto.UserAccountDto;
import com.fastcampus.projectboard.service.ArticleService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@DisplayName("View 컨트롤러 - 게시글 페이지")
@Import(SecurityConfig.class)
@WebMvcTest(ArticleController.class)
class ArticleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ArticleService articleService;



    //articles

    @DisplayName("View [GET] 게시글 리스트 페이지 정상 호출")
    @Test
    public void viewTestGetList()  throws Exception{

        //given
        given(articleService.searchArticles(eq(null), eq(null), any(Pageable.class))).willReturn(Page.empty());

        //when
        mvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("/articles/index"))
                .andExpect(model().attributeExists("articles"));

        //then
        then(articleService).should().searchArticles(eq(null), eq(null), any(Pageable.class));
    }

    //articles/{article-id}

    @DisplayName("View [GET] 게시글 상세 페이지 정상 호출")
    @Test
    public void viewTestGetFirst() throws Exception{
        Long articleId = 1L;
        //given
        given(articleService.getArticle(articleId)).willReturn(createArticleWithCommentsDto());

        //when
        mvc.perform(get("/articles/" + articleId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("/articles/detail"))
                .andExpect(model().attributeExists("article"))
                .andExpect(model().attributeExists("articleComments"));

        //then
        then(articleService).should().getArticle(articleId);
    }


    //articles/search
    @Disabled("구현 중")
    @DisplayName("View [GET] 게시글 검색 전용 페이지 정상 호출")
    @Test
    public void viewTestGetSearch() throws Exception{
        //given

        //when
        mvc.perform(get("/articles/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("/articles/search"));


        //then
    }

    @Disabled("구현 중")
    @DisplayName("View [GET] 게시글 해시태그 검색 페이지 정상 호출")
    @Test
    public void viewTestGetHashTag() throws Exception{
        //given

        //when
        mvc.perform(get("/articles/search-hastag"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("/articles/search-hashtag"));

        //then
    }

    private ArticleWithCommentsDto createArticleWithCommentsDto() {
        return ArticleWithCommentsDto.of(
                1L,
                createUserAccountDto(),
                Set.of(),
                "title",
                "content",
                "#java",
                LocalDateTime.now(),
                "lee",
                LocalDateTime.now(),
                "lee"
        );
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                "lee",
                "pw",
                "lee@mail.com",
                "lee",
                "memo",
                LocalDateTime.now(),
                "lee",
                LocalDateTime.now(),
                "lee"
        );
    }
}