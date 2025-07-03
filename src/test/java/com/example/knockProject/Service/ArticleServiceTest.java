package com.example.knockProject.Service;

import com.example.knockProject.dto.ArticleForm;
import com.example.knockProject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    @Test // 이 함수가 테스트 코드임을 선언
    void articles() { //TDD : Test Driven Development
        // 예상하는 데이터
        Article a = new Article(1L, "김삿갓", "동해물과 백두산이");
        Article b = new Article(2L, "이몽룡", "마르고 닭도록");
        Article c = new Article(3L, "선녀", "하느님이 보우하사");
        Article d = new Article(4L, "나뭇꾼", "우리나라 만세");
        List<Article> expected = new ArrayList<>(Arrays.asList(a,b,c,d));

        // 실제 데이터
        List<Article> articles = articleService.articles();

        // 비교 검증 예상 데이터와 실데이터를 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void article_suc() {
        Long id = 3L;
        //예상 데이터 생성
        Article expected = new Article(id, "선녀", "하느님이 보우하사");
        //실제 데이터
        Article article = articleService.article(id);

        //비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void article_fail() {
        Long id = -1L;
        //예상
        //Article expected = null;

        //실제
        Article article = articleService.article(id);

        //비교
        assertEquals(null, article);
    }

    @Test
    void create() {
        String title = "내맘";
        String content = "내맘";
        ArticleForm testDto = new ArticleForm(null, title, content);

        //예상
        Article expected = new Article(5L, title, content);

        //실제
        Article article = articleService.create(testDto);

        //비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void create_fail() {
        Long id = 3L;
        String title = "3333";
        String content = "3333";
        ArticleForm testDto = new ArticleForm(id, title, content);

        //예상
        Article expected = null;

        //실제
        Article article = articleService.create(testDto);

        //비교
        assertEquals(expected, article);
    }
}