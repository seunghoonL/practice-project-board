package com.fastcampus.projectboard.domain;

import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(callSuper = true)
@Entity
@Table(name = "article")
public class Article extends AuditingFields{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    @Setter @Column(nullable = false)
    private String title; // 제목

    @Setter @Column(length = 10000, nullable = false)
    private String content; // 본문

    @Setter
    private String hashtag; // 해시태그

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @OrderBy("id")
    @ToString.Exclude
    private final Set<ArticleComment> articleComments = new HashSet<>();




    private Article(UserAccount userAccount, String title, String content, String hashtag) {
        this.userAccount = userAccount;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }


    public static Article of(UserAccount userAccount, String title, String content, String hashtag) {
        return new Article(userAccount, title, content, hashtag);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return id != null && Objects.equals(id, article.id);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
