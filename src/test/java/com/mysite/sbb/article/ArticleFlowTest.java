package com.mysite.sbb.article;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ArticleFlowTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArticleRepository articleRepository;

    @org.junit.jupiter.api.Test
    @DisplayName("루트 URL 접속 시 게시글 리스트로 이동한다")
    void rootRedirectsToArticleList() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/article/list"));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("게시글 리스트와 등록 폼은 article 경로로 제공된다")
    void articlePagesAreServed() throws Exception {
        mockMvc.perform(get("/article/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("article_list"))
                .andExpect(content().string(containsString("/article/create")))
                .andExpect(content().string(containsString("/article/detail/")));

        mockMvc.perform(get("/article/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("article_form"));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("게시글 등록 시 저장 후 게시글 리스트로 리다이렉트된다")
    void createArticleStoresDataAndRedirects() throws Exception {
        long beforeCount = articleRepository.count();

        mockMvc.perform(post("/article/create")
                        .param("title", "테스트 게시글")
                        .param("content", "테스트 게시글 내용"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/article/list"));

        Article createdArticle = articleRepository.findByTitle("테스트 게시글").orElseThrow();
        assertThat(articleRepository.count()).isEqualTo(beforeCount + 1);
        assertThat(createdArticle.getContent()).isEqualTo("테스트 게시글 내용");
    }

    @org.junit.jupiter.api.Test
    @DisplayName("게시글 상세 페이지에는 게시글 내용과 목록 버튼이 있다")
    void articleDetailContainsContentAndListButton() throws Exception {
        mockMvc.perform(get("/article/detail/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("article_detail"))
                .andExpect(content().string(containsString("sbb가 무엇인가요?")))
                .andExpect(content().string(containsString("/article/list")));
    }
}



