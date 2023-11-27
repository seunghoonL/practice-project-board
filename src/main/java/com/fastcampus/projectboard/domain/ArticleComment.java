package com.fastcampus.projectboard.domain;

import lombok.*;
import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.FetchType.*;


@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "article_comment")
public class ArticleComment extends AuditingFields {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Setter @ManyToOne(fetch = LAZY, optional = false)
    @ToString.Exclude
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
        if (!(o instanceof ArticleComment)) return false;
        ArticleComment that = (ArticleComment) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
