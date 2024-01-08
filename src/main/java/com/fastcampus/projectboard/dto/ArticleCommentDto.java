package com.fastcampus.projectboard.dto;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.ArticleComment;
import com.fastcampus.projectboard.domain.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.fastcampus.projectboard.domain.ArticleComment}
 */

public record ArticleCommentDto(

    Long id,

    Long articleId,

    UserAccountDto userAccountDto,

    LocalDateTime createdAt,

    String createdBy,

    LocalDateTime modifiedAt,

    String modifiedBy,

    String content){


    public static ArticleCommentDto of(LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy, String content) {
        return ArticleCommentDto.of(null, null, null, createdAt, createdBy, modifiedAt, modifiedBy, content);
    }



    public static ArticleCommentDto of(Long id, Long articleId, UserAccountDto userAccountDto, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy, String content) {
        return new ArticleCommentDto(id, articleId, userAccountDto, createdAt, createdBy, modifiedAt, modifiedBy, content);
    }



    public static ArticleCommentDto from(ArticleComment entity){
        return new ArticleCommentDto(
                entity.getId(),
                entity.getArticle().getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy(),
                entity.getContent()
                );

    }


    public ArticleComment toEntity(Article article) {
        return ArticleComment.of(
                article.getUserAccount(),
                article,
                content
        );
    }

}