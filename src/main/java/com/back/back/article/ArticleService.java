package com.back.back.article;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {
	private final ArticleRepository articleRepository;

	public List<Article> findAll() {
		return articleRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	public Article findById(Long id) {
		return articleRepository.findById(id)
				.orElseThrow(() -> new ArticleNotFoundException(id));
	}

	@Transactional
	public Article create(String title, String content) {
		return articleRepository.save(new Article(title, content));
	}
}
