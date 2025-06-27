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

import java.util.ArrayList;

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
        return "redirect:/article/"+saved.getId(); // 리다이렉트 -> redirect:주소
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

    @GetMapping("/articles")
    public String articleAll(Model model){
        // Down Casting
        //List<Article> articleList = (List<Article>)articleRepository.findAll();
        //Iterator<Article> articleList = articleRepository.findAll();
        ArrayList<Article> articleList = articleRepository.findAll();


        // 모델에 데이터 등록
        model.addAttribute("articles", articleList);

        return "articles/List";
    }

    @GetMapping("/img")
    public String imageShow(Model model){

        model.addAttribute("imagePath", "/Images/2222.png");

        return "articles/ImageShow";
    }

    // 상세 페이지에서 수정 페이지 오픈
    @GetMapping("/article/update/{number}")
    public String articleUpdate(@PathVariable Long number, Model model){

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

        return "articles/update";
    }

    // 수정 완료 버튼
    @PostMapping("/article/update/submit")
    public String articleUpdateSubmit(ArticleForm articleForm){
        //System.out.println("1.확인용 : " + articleForm.toString());
        // 로그 작성
        log.info(articleForm.toString());
        // DTO -> Entity
        Article article = articleForm.toEntity();
        //System.out.println("2.확인용 Article : " + article.toString());
        log.info(articleForm.toString());
        // Repo -> DB save
        article.SetId(number);
        Article saved = articleRepository.save(article);
        //System.out.println("3.확인용 Article save : " + saved.toString());
        log.info(saved.toString());
        return "redirect:/article/"+saved.getId(); // 리다이렉트 -> redirect:주소
    }




}
