package com.fastcampus.projectboard.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled("Spring Data Rest 통합 테스트는 불필요하므로 제외시킴")
@Transactional
@DisplayName("Data REST 테스트")
@AutoConfigureMockMvc
@SpringBootTest
public class DataRestTest {

    //          "self": {
    //            "href": "http://localhost:8080/api/articles/1"
    //          },
    //          "article": {
    //            "href": "http://localhost:8080/api/articles/1"
    //          },
    //          "articleComments": {
    //            "href": "http://localhost:8080/api/articles/1/articleComments"






    @Autowired
    private MockMvc mvc;


    @DisplayName("api 게시글 리스트 조회")
    @Test
    public void listSearch() throws Exception{
        //given

        //when
        mvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));

        //then
    }

    @DisplayName("api 게시글 1건 조회")
    @Test
    public void oneSearch() throws Exception{
        //given

        //when
        mvc.perform(get("/api/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
        //then
    }



    @DisplayName("api 댓글 리스트 조회")
    @Test
    public void listCommentSearch() throws Exception{
        //given

        //when
        mvc.perform(get("/api/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));

        //then
    }

    @DisplayName("api 댓글 단건 조회")
    @Test
    public void oneCommentSearch() throws Exception{
        //given

        //when
        mvc.perform(get("/api/articleComments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));

        //then
    }


}
