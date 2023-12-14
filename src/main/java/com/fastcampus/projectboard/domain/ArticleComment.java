package com.fastcampus.projectboard.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

import static jakarta.persistence.FetchType.LAZY;


@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "article_comment")
public class ArticleComment extends AuditingFields {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "user_account_user_id")
    private UserAccount userAccount;

    @Setter @ManyToOne(fetch = LAZY, optional = false)
    @ToString.Exclude @JoinColumn(name = "article_id")
    private Article article; // Article Id

    @Setter @Column(length = 500, nullable = false)
    private String content; // 본문






    private ArticleComment(Article article, String content) {
        this.article = article;
        this.content = content;
    }


    public static ArticleComment of(Article article, String content){
        return new ArticleComment(article, content);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment that)) return false;
        return id != null && this.id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
