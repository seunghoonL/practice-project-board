package com.fastcampus.projectboard.controller;

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
@WebMvcTest(ArticleControllerTest.class)
class ArticleControllerTest {

    private final MockMvc mvc;

    public ArticleControllerTest(@Autowired MockMvc mockMvc) {
        this.mvc = mockMvc;
    }

    //articles
    @DisplayName("View [GET] 게시글 리스트 페이지 정상 호출")
    @Test
    public void viewTestGetList()  throws Exception{
        //given

        //when
        mvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
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
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(model().attributeDoesNotExist("article"));

        //then
    }


    //articles/search
    @DisplayName("View [GET] 게시글 검색 전용 페이지 정상 호출")
    @Test
    public void viewTestGetSearch() throws Exception{
        //given

        //when
        mvc.perform(get("/articles/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML));


        //then
    }


    @DisplayName("View [GET] 게시글 해시태그 검색 페이지 정상 호출")
    @Test
    public void viewTestGetHashTag() throws Exception{
        //given

        //when
        mvc.perform(get("/articles/search-hastag"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML));

        //then
    }
}