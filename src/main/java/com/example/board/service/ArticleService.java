package com.example.board.service;

import com.example.board.entity.Article;
import com.example.board.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    // 게시글 전체 조회 (최신순)
    @Transactional(readOnly = true)
    public List<Article> findAll() {
        return articleRepository.findAllByOrderByCreatedAtDesc();
    }

    // 게시글 단건 조회
    @Transactional(readOnly = true)
    public Article findById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));
    }

    // 게시글 저장
    @Transactional
    public Article save(Article article) {
        return articleRepository.save(article);
    }
}
