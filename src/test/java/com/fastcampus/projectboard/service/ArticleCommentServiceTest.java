package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.ArticleComment;
import com.fastcampus.projectboard.domain.UserAccount;
import com.fastcampus.projectboard.dto.ArticleCommentDto;
import com.fastcampus.projectboard.dto.UserAccountDto;
import com.fastcampus.projectboard.repository.ArticleCommentRepository;
import com.fastcampus.projectboard.repository.ArticleRepository;
import com.fastcampus.projectboard.repository.UserAccountRepository;
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

    @Mock
    private UserAccountRepository userAccountRepository;



    @DisplayName("게시판 id로 조회하여 댓글 리스트 가져오기")
    @Test
     void searchArticleIdAndGetArticleComments() throws Exception{
        //given
        Long articleId = 1L;
        List<ArticleComment> articleComment = List.of(createArticleComment());
        given(articleCommentRepository.findByArticle_Id(articleId)).willReturn(articleComment);

        //when
        List<ArticleCommentDto> articleComments =  sut.searchArticleComments(articleId);

        //then
        assertThat(articleComments).isNotNull();
        then(articleCommentRepository).should().findByArticle_Id(articleId);
    }


    @DisplayName("댓글 정보를 입력하면 댓글을 저장한다.")
    @Test
     void saveArticleComment() throws Exception{
        //given
        ArticleCommentDto dto = createArticleCommentDto("댓글");
        Long articleId = 1L;

        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);
        given(articleRepository.getReferenceById(dto.articleId())).willReturn(createArticle());
        given(userAccountRepository.getReferenceById(dto.userAccountDto().userId())).willReturn(createUserAccount());

        //when
        sut.saveArticleComment(dto);

        //then
        //assertThat(articleComments).isNotNull();
        then(articleCommentRepository).should().save(any(ArticleComment.class));
        then(articleRepository).should().getReferenceById(dto.articleId());
        then(userAccountRepository).should().getReferenceById(dto.userAccountDto().userId());
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

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                "lee",
                "password",
                "lee@mail.com",
                "lee",
                "This is memo",
                LocalDateTime.now(),
                "lee",
                LocalDateTime.now(),
                "lee"
        );
    }

    private ArticleCommentDto createArticleCommentDto(String content){
        return ArticleCommentDto.of(
                1L,
                createUserAccountDto()
                ,content);
    }

    private ArticleComment createArticleComment(){
        return ArticleComment.of(
                createUserAccount(),
                createArticle()
                , "content");
    }


    private Article createArticle(){
        return Article.of(createUserAccount(),
                "title",
                "content",
                "#java");
    }






}