package com.fastcampus.projectboard.dto.response;

import com.fastcampus.projectboard.dto.ArticleDto;

import java.time.LocalDateTime;

public record ArticleResponse(Long id,
                              String title,
                              String content,
                              String hashtag,
                              LocalDateTime createdAt,
                              String email,
                              String nickName) {


    public static ArticleResponse of(Long id, String title, String content, String hashtag, LocalDateTime createdAt, String email, String nickName){
        return new ArticleResponse(id, title, content, hashtag, createdAt, email, nickName);
    }


    public static ArticleResponse from(ArticleDto dto){
        String nickname = dto.userAccountDto().nickname();
        if (nickname == null || nickname.isBlank()){
            nickname = dto.userAccountDto().userId();
        }

        return new ArticleResponse(
                dto.id(),
                dto.title(),
                dto.content(),
                dto.hashtag(),
                dto.createdAt(),
                dto.userAccountDto().email(),
                nickname);
    }
}