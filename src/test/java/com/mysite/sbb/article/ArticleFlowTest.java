package com.mysite.sbb.article;

import com.mysite.sbb.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class ArticleFlowTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserService userService;

    @org.junit.jupiter.api.Test
    @DisplayName("루트 URL 접속 시 게시글 리스트로 이동한다")
    void rootRedirectsToArticleList() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/article/list"));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("로그인/회원가입 페이지는 접근 가능하다")
    void loginSignupPagesAreAccessible() throws Exception {
        mockMvc.perform(get("/user/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/login"));

        mockMvc.perform(get("/user/signup"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/signup"));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("게시글 리스트는 로그인 없이 접근 가능하다")
    void articleListIsAccessibleWithoutLogin() throws Exception {
        mockMvc.perform(get("/article/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("article_list"));
    }

    @org.junit.jupiter.api.Test
    @WithMockUser
    @DisplayName("게시글 등록은 인증된 사용자만 접근 가능하다")
    void createArticleRequiresAuthentication() throws Exception {
        mockMvc.perform(get("/article/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("article_form"));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("게시글 등록은 인증되지 않은 사용자는 접근 불가능하다")
    void createArticleIsForbiddenWithoutAuth() throws Exception {
        mockMvc.perform(get("/article/create"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/user/login"));
    }

    @org.junit.jupiter.api.Test
    @WithMockUser
    @DisplayName("게시글 등록 시 저장 후 게시글 리스트로 리다이렉트된다")
    void createArticleStoresDataAndRedirects() throws Exception {
        try {
            userService.create("user", "테스트유저", "test1234");
        } catch (IllegalArgumentException ignored) {
        }

        long beforeCount = articleRepository.count();

        mockMvc.perform(post("/article/create")
                        .with(csrf())
                        .param("title", "테스트 게시글")
                        .param("content", "테스트 게시글 내용"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/article/list"));

        Article createdArticle = articleRepository.findByTitle("테스트 게시글").orElseThrow();
        assertThat(articleRepository.count()).isEqualTo(beforeCount + 1);
        assertThat(createdArticle.getContent()).isEqualTo("테스트 게시글 내용");
    }

    @org.junit.jupiter.api.Test
    @WithMockUser(username = "other")
    @DisplayName("본인 글이 아니면 수정 접근이 거부된다")
    void modifyArticleForbiddenForNonAuthor() throws Exception {
        try {
            userService.create("writer", "작성자", "test1234");
        } catch (IllegalArgumentException ignored) {
        }

        Article article = new Article();
        article.setTitle("권한 테스트");
        article.setContent("작성자만 수정 가능");
        article.setAuthor(userService.getUser("writer"));
        articleRepository.save(article);

        mockMvc.perform(get("/article/modify/" + article.getId()))
                .andExpect(status().isForbidden())
                .andExpect(status().reason("본인 글만 수정할 수 있습니다."));
    }

    @org.junit.jupiter.api.Test
    @WithMockUser(username = "owner")
    @DisplayName("본인 글이면 수정 접근이 허용된다")
    void modifyArticleAllowedForAuthor() throws Exception {
        try {
            userService.create("owner", "작성자본인", "test1234");
        } catch (IllegalArgumentException ignored) {
        }

        Article article = new Article();
        article.setTitle("본인 수정 테스트");
        article.setContent("본인은 수정 가능");
        article.setAuthor(userService.getUser("owner"));
        articleRepository.save(article);

        mockMvc.perform(get("/article/modify/" + article.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("article_form"));
    }

    @org.junit.jupiter.api.Test
    @WithMockUser(username = "other")
    @DisplayName("본인 글이 아니면 삭제 접근이 거부된다")
    void deleteArticleForbiddenForNonAuthor() throws Exception {
        try {
            userService.create("writer2", "작성자2", "test1234");
        } catch (IllegalArgumentException ignored) {
            // 이미 생성된 경우 테스트 흐름에 영향 없음
        }

        Article article = new Article();
        article.setTitle("삭제 권한 테스트");
        article.setContent("작성자만 삭제 가능");
        article.setAuthor(userService.getUser("writer2"));
        articleRepository.save(article);

        mockMvc.perform(post("/article/delete/" + article.getId()).with(csrf()))
                .andExpect(status().isForbidden())
                .andExpect(status().reason("본인 글만 삭제할 수 있습니다."));
    }

    @org.junit.jupiter.api.Test
    @WithMockUser(username = "owner2")
    @DisplayName("본인 글이면 삭제가 허용된다")
    void deleteArticleAllowedForAuthor() throws Exception {
        try {
            userService.create("owner2", "작성자본인2", "test1234");
        } catch (IllegalArgumentException ignored) {
        }

        Article article = new Article();
        article.setTitle("본인 삭제 테스트");
        article.setContent("본인은 삭제 가능");
        article.setAuthor(userService.getUser("owner2"));
        articleRepository.save(article);

        mockMvc.perform(post("/article/delete/" + article.getId()).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/article/list"));

        assertThat(articleRepository.findById(article.getId())).isEmpty();
    }
}
