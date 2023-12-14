package com.fastcampus.projectboard.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.fastcampus.projectboard.domain.Article}
 */
@Value
public class ArticleDto  {
    LocalDateTime createdAt;

    String createdBy;

    String title;

    String content;

    String hashtag;


    public static ArticleDto of(LocalDateTime createdAt, String createdBy, String title, String content, String hashtag) {
        return new ArticleDto(createdAt, createdBy, title, content, hashtag);
    }
}