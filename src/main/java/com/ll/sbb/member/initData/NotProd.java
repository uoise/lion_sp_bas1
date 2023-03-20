package com.ll.sbb.member.initData;

import com.ll.sbb.article.model.Article;
import com.ll.sbb.article.service.ArticleService;
import com.ll.sbb.member.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"dev", "test"})
public class NotProd {

    @Bean
    public CommandLineRunner init(MemberService memberService, ArticleService articleService) {
        return args -> {
            memberService.register("user1", "1234");
            memberService.register("abc", "12345");
            memberService.register("test", "12346");
            memberService.register("love", "12347");
            memberService.register("like", "12348");
            memberService.register("giving", "12349");
            memberService.register("thanks", "123410");
            memberService.register("hello", "123411");
            memberService.register("good", "123412");
            memberService.register("peace", "123413");

            articleService.create(Article.builder().title("제목1").body("내용1").build());
            articleService.create(Article.builder().title("제목2").body("내용2").build());
        };
    }
}