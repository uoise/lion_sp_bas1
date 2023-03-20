package com.ll.sbb.article.service;

import com.ll.sbb.article.model.Article;
import com.ll.sbb.article.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional
    public long create(Article article) {
        return articleRepository.save(article).getId();
    }

    @Transactional
    public long update(Article article) {
        Article fnd = articleRepository.findById(article.getId()).orElse(null);
        if (fnd == null) return create(article);
        return articleRepository.save(fnd).getId();
    }

    @Transactional
    public Article findByTitle(String title) {
        return articleRepository.findArticleByTitle(title).orElse(null);
    }
}
