package com.mysite.sbb;

import com.mysite.sbb.article.Article;
import com.mysite.sbb.article.ArticleRepository;
import com.mysite.sbb.user.User;
import com.mysite.sbb.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
@Profile("!test")
public class TestInitData {

    private final ArticleRepository articleRepository;
    private final UserService userService;

    @Bean
    ApplicationRunner initData() {
        return args -> {
            if (articleRepository.count() > 0) {
                return;
            }

            User initAuthor;
            try {
                initAuthor = userService.create("init_author", "초기작성자", "init1234");
            } catch (IllegalArgumentException e) {
                initAuthor = userService.getUser("init_author");
            }

            Article article1 = new Article();
            article1.setTitle("sbb가 무엇인가요?");
            article1.setContent("sbb에 대해서 알고 싶습니다.");
            article1.setAuthor(initAuthor);
            article1.setCreatedDate(LocalDateTime.now());
            articleRepository.save(article1);

            Article article2 = new Article();
            article2.setTitle("스프링부트 모델 질문입니다.");
            article2.setContent("id는 자동으로 생성되나요?");
            article2.setAuthor(initAuthor);
            article2.setCreatedDate(LocalDateTime.now());
            articleRepository.save(article2);
        };
    }
}

