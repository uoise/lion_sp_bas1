package com.ll.sbb.article.repository;

import com.ll.sbb.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleRepository extends JpaRepository<Article, Long> {

}
