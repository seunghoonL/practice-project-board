package com.fastcampus.projectboard.dto;

import com.fastcampus.projectboard.domain.UserAccount;

import java.time.LocalDateTime;

/**
 * DTO for {@link UserAccount}
 */
public record UserAccountDto(
                             String userId,
                             String userPassword,
                             String email,
                             String nickname,
                             String memo,
                             LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {



    public static UserAccountDto of(String userId, String userPassword, String email, String nickname, String memo){
        return new UserAccountDto(userId, userPassword, email, nickname, memo, null, null, null, null );
    }


    public static UserAccountDto of(String userId, String userPassword, String email, String nickname, String memo,
                                    LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy){
        return new UserAccountDto(userId, userPassword, email, nickname, memo, createdAt, createdBy, modifiedAt, modifiedBy );
    }
}