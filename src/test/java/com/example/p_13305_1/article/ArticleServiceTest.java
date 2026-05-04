package com.example.p_13305_1.article;

import com.example.p_13305_1.global.exception.DataNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleRepository articleRepository;

    private Integer testArticleId;

    @BeforeEach
    void setUp() {
        Article article = Article.builder()
                .title("테스트 제목")
                .content("테스트 내용")
                .build();
        Article saved = articleRepository.save(article);

        this.testArticleId = saved.getId();
    }

    @Test
    void getById_성공() {
        // when
        Article article = articleService.getById(testArticleId);

        // then
        assertNotNull(article);
        assertEquals("테스트 제목", article.getTitle());
    }

    @Test
    void getById_실패_예외발생() {
        // given
        Integer nonExistId = 99999;

        // when & then
        assertThrows(DataNotFoundException.class, () -> {
            articleService.getById(nonExistId);
        });
    }

    @Test
    void getAllArticles_조회() {
        // when
        List<Article> articles = articleService.getAllArticles();

        // then
        assertFalse(articles.isEmpty());
    }

    @Test
    void updateArticle_수정검증() {
        // when
        articleService.updateArticle(testArticleId, "수정된 제목", "수정된 내용");

        // then
        Article updated = articleService.getById(testArticleId);
        assertEquals("수정된 제목", updated.getTitle());
        assertEquals("수정된 내용", updated.getContent());
    }
}