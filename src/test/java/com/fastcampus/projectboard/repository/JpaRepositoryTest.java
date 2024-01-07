package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.config.JpaConfig;
import com.fastcampus.projectboard.domain.Article;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("JPA 연결 테스트")
@SpringBootTest
@Transactional
class JpaRepositoryTest {

    @Autowired
    private  ArticleRepository articleRepository;
    @Autowired
    private  ArticleCommentRepository articleCommentRepository;




    @DisplayName("select 테스트")
    @Test
    public void selectTest() throws Exception{
        //given

        //when

        Article article = articleRepository.findById(1L).orElse(null);


        //then
        assertThat(article).isNotNull();
        System.out.println(article);

    }

    @DisplayName("insert 테스트")
    @Test
    public void insertTest() throws Exception{
        //given

        var entity = Article.of(null,"hello", "nono", "#tg");
        //when
        Article saveEntity = articleRepository.save(entity);
        var count = articleRepository.count();
        //then
        assertThat(saveEntity).isNotNull();
        System.out.println(count);

    }

    @DisplayName("update 테스트")
    @Test
    public void updateTest() throws Exception{
        //given
        Article article = articleRepository.findById(1L).orElse(null);
        //when
        article.setTitle("changed");

        articleRepository.flush();
        //then
        var result = articleRepository.findById(1L).orElse(null);
        assertThat(result.getTitle()).isEqualTo("changed");
    }


    @DisplayName("delete 테스트")
    @Test
    public void deleteTest() throws Exception{
        //given
        Article article = articleRepository.findById(1L).orElse(null);

        //when
        articleRepository.delete(article);
        articleRepository.flush();

        //then

    }
}