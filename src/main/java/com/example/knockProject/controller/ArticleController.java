package com.example.knockProject.controller;

import com.example.knockProject.Repository.ArticleRepository;
import com.example.knockProject.dto.ArticleForm;
import com.example.knockProject.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ArticleController {
    // 의존성 주입
    // 의존성 주입 기능으로 오버라이딩을 생략 할 수 있다.
    @Autowired // 의존성 주입 어노테이션 DI
    private ArticleRepository articleRepository;

    @GetMapping("/article/new")
    public String newArticleForm()
    {
        return "articles/new";
    }

    @PostMapping("/article/create") // 생성 요청
    public String newArticle(ArticleForm articleForm) // DTO로 데이터로 수집
    {
        //System.out.println("1.확인용 : " + articleForm.toString());
        // 로그 작성
        log.info(articleForm.toString());
        // DTO -> Entity
        Article article = articleForm.toEntity();
        //System.out.println("2.확인용 Article : " + article.toString());
        log.info(articleForm.toString());
        // Repo -> DB save
        Article saved = articleRepository.save(article);
        //System.out.println("3.확인용 Article save : " + saved.toString());
        log.info(saved.toString());
        return "articles/new";
    }

    @GetMapping("/article/{number}")
    public String articleShow(@PathVariable Long number, Model model){
        //PathVariable 요청 변수 수집

        log.info("number : " + number);

        //게시글 번호 확인해서 view 처리

        // 받아온 아이디 값을 db로 조회
        //Optional<Article> saved = articleRepository.findById(number);
        // 조회시 없을 떄 null을 리턴 한다는 것
        Article saved = articleRepository.findById(number).orElse(null);

        // view 화면에 보여주기
        // addAtrb 활용하여 모델에 데이터 등록
        model.addAttribute("article", saved);

        return "articles/show";
    }


}
