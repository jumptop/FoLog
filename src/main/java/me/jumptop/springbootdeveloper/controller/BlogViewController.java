package me.jumptop.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.jumptop.springbootdeveloper.domain.Article;
import me.jumptop.springbootdeveloper.dto.ArticleListViewResponse;
import me.jumptop.springbootdeveloper.dto.ArticleResponse;
import me.jumptop.springbootdeveloper.dto.ArticleViewResponse;
import me.jumptop.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        // ArticleListViewResponse 자료형을 가진 리스트를 articles 변수에 저장, blogService에 있는 모든것을 찾는 메서드 호출, stream을 통해 그 모든것에서 하나씩 가져옴
        // map은 스트림 안의 각 원소를 다른 것으로 변환 -> Stream<Article>을 Stream<ArticleListViewResponse>로 변환
        // 이후 to List를 사용하여 다시 List로 변환 그래서 최종 타입은 List<ArticleListViewResponse>가 됨
        List<ArticleListViewResponse> articles = blogService.findAll().stream().map(ArticleListViewResponse::new).toList();
        model.addAttribute("articles", articles); // 블로그 글 리스트 저장

        return "articleList"; // articleList.html 조회
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }
}
