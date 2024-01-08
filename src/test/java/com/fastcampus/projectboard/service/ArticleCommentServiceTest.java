package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.ArticleComment;
import com.fastcampus.projectboard.domain.UserAccount;
import com.fastcampus.projectboard.dto.ArticleCommentDto;
import com.fastcampus.projectboard.repository.ArticleCommentRepository;
import com.fastcampus.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks
    private ArticleCommentService sut;

    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private ArticleCommentRepository articleCommentRepository;



    @DisplayName("게시판 id로 조회하여 댓글 리스트 가져오기")
    @Test
    public void searchArticleIdAndGetArticleComments() throws Exception{
        //given
        Long articleId = 1L;
        Optional<Article> articleOptional = Optional.of(Article.of(createUserAccount(),"hello", "content", "#hello"));
        given(articleRepository.findById(articleId)).willReturn(articleOptional);

        //when
        List<ArticleCommentDto> articleComments =  sut.searchArticleComments(articleId);

        //then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }


    @DisplayName("댓글 정보를 입력하면 댓글을 저장한다.")
    @Test
    public void saveArticleComment() throws Exception{
        //given
        Long articleId = 1L;
        ArticleCommentDto articleCommentDto = ArticleCommentDto.of(LocalDateTime.now(), "hoon", LocalDateTime.now(), "hoon", "content");
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);


        //when
        //sut.saveArticleComment(articleCommentDto);

        //then
        //assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }




    private UserAccount createUserAccount() {
        return UserAccount.of(
                "lee",
                "password",
                "lee@email.com",
                "lee",
                null
        );
    }






}