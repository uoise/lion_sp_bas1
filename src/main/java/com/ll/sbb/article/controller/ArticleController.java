package com.ll.sbb.article.controller;

import com.ll.sbb.article.model.Article;
import com.ll.sbb.article.service.ArticleService;
import com.ll.sbb.rspData.model.RspData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/write")
    @ResponseBody
    public RspData write(@RequestParam String title, String body) {
        Article article = Article.builder()
                .title(title)
                .body(body)
                .build();
        long articleId = articleService.create(article);
        return RspData.of("S-1", articleId + "번 글이 생성되었습니다");
    }

    @GetMapping("/find")
    @ResponseBody
    public Article find(@RequestParam String title) {
        return articleService.findByTitle(title);
    }
}
