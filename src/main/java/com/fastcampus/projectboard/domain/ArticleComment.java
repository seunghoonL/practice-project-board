package com.fastcampus.projectboard.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "article_comment")
public class ArticleComment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Setter @ManyToOne(optional = false)
    private Article article; // Article Id

    @Setter @Column(length = 500, nullable = false)
    private String content; // 본문




    @CreatedDate @Column(nullable = false)
    private LocalDateTime createdAt; // 생성일시
    @CreatedBy  @Column(length = 100, nullable = false)
    private String createdBy; // 생성자
    @LastModifiedDate @Column(nullable = false)
    private LocalDateTime modifiedAt; // 수정일시
    @LastModifiedBy @Column(length = 100, nullable = false)
    private String modifiedBy; // 수정자



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
