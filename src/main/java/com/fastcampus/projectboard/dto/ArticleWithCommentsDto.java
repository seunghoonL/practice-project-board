package com.fastcampus.projectboard.dto;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.ArticleComment;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO for {@link Article}
 */

public record ArticleWithCommentsDto(

    Long id,
    UserAccountDto userAccountDto,
    Set<ArticleCommentDto> articleCommentDtos,
    String title,
    String content,
    String hashtag,

    LocalDateTime createdAt,
    String createdBy,

    LocalDateTime modifiedAt,

    String modifiedBy){



    public static ArticleWithCommentsDto of(Long id, UserAccountDto userAccountDto, Set<ArticleCommentDto> articleCommentDtos,
                                            String title, String content, String hashtag,
                                            LocalDateTime createdAt, String createdBy,
                                            LocalDateTime modifiedAt, String modifiedBy) {
        return new ArticleWithCommentsDto(id, userAccountDto, articleCommentDtos, title, content, hashtag
        , createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static ArticleWithCommentsDto from(Article article){
        return new ArticleWithCommentsDto(
                article.getId(),
                UserAccountDto.from(article.getUserAccount()),
                article.getArticleComments().stream()
                        .map(ArticleCommentDto::from)
                                .collect(Collectors.toCollection(LinkedHashSet::new)),
                article.getTitle(),
                article.getContent(),
                article.getHashtag(),
                article.getCreatedAt(),
                article.getCreatedBy(),
                article.getModifiedAt(),
                article.getModifiedBy()
        );
    }


    public Article toEntity(){
        return Article.of(
                userAccountDto.toEntity(),
                title,
                content,
                hashtag
        );
    }
}