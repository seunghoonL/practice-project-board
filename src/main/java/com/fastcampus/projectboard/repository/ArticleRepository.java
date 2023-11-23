package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>, // 모든 필드들 에 대해 검색 조건이 열림
        QuerydslBinderCustomizer<QArticle>{

        @Override
        default void customize(QuerydslBindings bindings, QArticle root){
                bindings.excludeUnlistedProperties(true); // 리스트에 없는 속성들은 검색 조건에 포함하지 않음
                bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy); // 검색 조건 생성
                bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
                bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // like'%${v}%
                //bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // like'${v}'
                bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
                bindings.bind(root.createdAt).first(DateTimeExpression::eq);
                bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
        }

}