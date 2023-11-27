package com.fastcampus.projectboard.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 게시글 페이지")
@WebMvcTest(ArticleController.class)
class ArticleControllerTest {

    @Autowired
    private MockMvc mvc;



    //articles

    @DisplayName("View [GET] 게시글 리스트 페이지 정상 호출")
    @Test
    public void viewTestGetList()  throws Exception{
        //given

        //when
        mvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("/articles/index"))
                .andExpect(model().attributeExists("articles"));

        //then
    }

    //articles/{article-id}

    @DisplayName("View [GET] 게시글 상세 페이지 정상 호출")
    @Test
    public void viewTestGetFirst() throws Exception{
        //given

        //when
        mvc.perform(get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("/articles/detail"))
                .andExpect(model().attributeExists("article"))
                .andExpect(model().attributeExists("articleComments"));

        //then
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
}