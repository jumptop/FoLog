package me.jumptop.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.jumptop.springbootdeveloper.domain.Article;
import me.jumptop.springbootdeveloper.dto.AddArticleRequest;
import me.jumptop.springbootdeveloper.dto.UpdateArticleRequest;
import me.jumptop.springbootdeveloper.repository.BlogRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service // 빈으로 등록
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    // 블로그 글을 모두 가져오는 메서드
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    // 블로그에서 글을 하나만 가져오는 메서드
    public Article findById(Long id) {
        return blogRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artile not found with id " + id));
    }

    // 블로그에서 글을 삭제하는 메서드
    public void deleteById(Long id) {
        blogRepository.deleteById(id);
    }

    // 블로그에서 글을 수정하는 메서드
    @Transactional // 트랜잭션 메서드
    public Article updateById(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
