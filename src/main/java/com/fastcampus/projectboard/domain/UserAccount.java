package com.fastcampus.projectboard.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAccount extends AuditingFields {

    @Id
    @Column(length = 50)
    private String userId;

    @Column(length = 255, nullable = false) @Setter
    private String userPassword;

    @Column(length = 100, nullable = false) @Setter
    private String email;

    @Column(length = 100, nullable = false) @Setter
    private String nickname;

    @Column(length = 255) @Setter
    private String memo;


    private UserAccount(String userId, String userPassword, String email, String nickname, String memo, String createdBy) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = email;
        this.nickname = nickname;
        this.memo = memo;
        this.createdBy = createdBy;
    }


    public static UserAccount of(String userId, String userPassword, String email, String nickname, String memo){
        return new UserAccount(userId, userPassword, email, nickname, memo, null);
    }


    public static UserAccount of(String userId, String userPassword, String email, String nickname, String memo, String createdBy){
        return new UserAccount(userId, userPassword, email, nickname, memo, createdBy);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount that)) return false;
        return this.userId != null &&  this.userId.equals(that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
