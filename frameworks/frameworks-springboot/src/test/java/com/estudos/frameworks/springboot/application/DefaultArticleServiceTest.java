package com.estudos.frameworks.springboot.application;

import com.estudos.frameworks.springboot.domain.Article;
import com.estudos.frameworks.springboot.domain.ArticleNotFoundException;
import com.estudos.frameworks.springboot.domain.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private DefaultArticleService articleService;

    @Test
    void createPersisteArtigo() {
        Article saved = new Article(1L, "T", "B", Instant.parse("2025-01-01T00:00:00Z"));
        when(articleRepository.save(any(Article.class))).thenReturn(saved);

        Article result = articleService.create("T", "B");

        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.title()).isEqualTo("T");
        verify(articleRepository).save(any(Article.class));
    }

    @Test
    void getByIdLancaQuandoAusente() {
        when(articleRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> articleService.getById(99L))
                .isInstanceOf(ArticleNotFoundException.class);
    }

    @Test
    void findAllOrdenaPorId() {
        when(articleRepository.findAll()).thenReturn(List.of(
                new Article(2L, "b", "", Instant.now()),
                new Article(1L, "a", "", Instant.now())));

        List<Article> all = articleService.findAll();

        assertThat(all).extracting(Article::id).containsExactly(1L, 2L);
    }
}
