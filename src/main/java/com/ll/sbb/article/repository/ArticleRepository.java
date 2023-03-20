package com.ll.sbb.article.repository;

import com.ll.sbb.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ArticleRepository extends JpaRepository<Article, Long> {
    public Optional<Article> findArticleByTitle(String title);
}
