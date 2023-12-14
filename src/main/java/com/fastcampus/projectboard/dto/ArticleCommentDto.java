package com.fastcampus.projectboard.dto;

import lombok.Value;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.fastcampus.projectboard.domain.ArticleComment}
 */
@Value
public class ArticleCommentDto {
    LocalDateTime createdAt;

    String createdBy;

    LocalDateTime modifiedAt;

    String modifiedBy;

    String content;


    public static ArticleCommentDto of(LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy, String content) {
        return new ArticleCommentDto(createdAt, createdBy, modifiedAt, modifiedBy, content);
    }
}