package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.type.SearchType;
import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.dto.ArticleUpdateDto;
import com.fastcampus.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;


@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks
    private ArticleService sut;
    @Mock
    private ArticleRepository articleRepository;


    @DisplayName("게시글을 검색하면 게시글 리스트를 반환한다.")
    @Test
    public void searchParameterThenReturnArticles() throws Exception{
        //given

        //when
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "searchKeyword");

        //then
        assertThat(articles).isNotNull();
    }


    @DisplayName("게시글을 조회하면 게시글을 반환한다.")
    @Test
    public void searchIdThenReturnArticle() throws Exception{
        //given

        //when
        ArticleDto article = sut.searchArticle(1L);

        //then
        assertThat(article).isNotNull();
    }

    @DisplayName("게시글 정보를 입력하면 게시글 생성")
    @Test
    public void writeArticle() throws Exception{
        //given
        ArticleDto dto = ArticleDto
                .of(LocalDateTime.now(), "hoon", "hello", "content", "#hello");

        given(articleRepository.save(any(Article.class))).willReturn(null);
        //when
        sut.saveArticle(dto);

        //then
        then(articleRepository).should().save(any(Article.class));

    }

    @DisplayName("게시글 ID로 조회한 게시글 수정")
    @Test
    public void updateArticle() throws Exception{
        //given
        ArticleUpdateDto updateDto = ArticleUpdateDto.of("hello", "content", "#hello");

        given(articleRepository.save(any(Article.class))).willReturn(null);
        //when
        sut.updateArticle(1L, updateDto);

        //then
        then(articleRepository).should().save(any(Article.class));

    }


    @DisplayName("게시글 ID로 조회하여 게시글 삭제")
    @Test
    public void deleteArticle() throws Exception{
        //given


        willDoNothing().given(articleRepository).delete(any(Article.class));
        //when
        sut.deleteArticle(1L);

        //then
        then(articleRepository).should().delete(any(Article.class));

    }






}