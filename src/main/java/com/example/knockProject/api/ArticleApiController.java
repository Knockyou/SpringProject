package com.example.knockProject.api;

import com.example.knockProject.Service.ArticleService;
import com.example.knockProject.dto.ArticleForm;
import com.example.knockProject.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// rest는 http통신규약에 맞춰 crud하는것
// api는 url 설계를 말한다. url을 정하는것

@RestController
@Slf4j
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;
//    @Autowired
//    private ArticleRepository articleRepository;
//
//    //Get
    @GetMapping("/api/articles")
    public List<Article> articles() {
//        return articleRepository.findAll();
        return articleService.articles();
    }
//
//    // 단일 게시글 조화
    @GetMapping("/api/article/{id}")
    public Article getArticle(@PathVariable Long id) {
        //return articleRepository.findById(id).orElse(null);
        return articleService.article(id);
    }
//
//    //Post
    @PostMapping("/api/articles")
    // 헤더,바디의 정보가 들어오는데 바디 정보만 필요하기 때문에 @RequestBody로 바디 정보만 받아 오게 한다
    public ResponseEntity<Article> createArticle(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);

        //Article article = dto.toEntity();
        //return articleRepository.save(article);

        return (created != null) ? ResponseEntity.status(HttpStatus.CREATED).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
//
//    //Patch
    @PatchMapping("/api/article/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody ArticleForm dto){
        Article updated = articleService.update(id, dto);
        // dto -> entity
//        Article article = dto.toEntity();
//        log.info("url_id = " + id + "," + article.toString());
//
//        // data -> validation
//        Article dbData = articleRepository.findById(id).orElse(null);
//
//        // if wrong req -> process
//        if(dbData == null || id != article.getId()){ // 정책의 의미도 포함 한다.
//            // 잘못된 경우 처리
//            log.info("잘못된 요청으로 인한 로그 남김");
//            log.info("url_id = " + id + ", " + article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        // update & response
//        // patch 함수 작업
//        // 변경온 부분만 업데이트를 할 수 있도록 처리
//        dbData.patch(article);
//
//        Article updated = articleRepository.save(dbData);
        return (updated != null) ? ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
//
//    //Delete
    @DeleteMapping("/api/article/{id}")
    public ResponseEntity<Article> deleteArticle(@PathVariable Long id) {
        Article deleted = articleService.delete(id);
        // DB에 데이터 존재 하는지 체크
//        Article article = articleRepository.findById(id).orElse(null);
//
//        // 데이터가 없다면 요청 실패 처리
//        if(article == null){
//            log.info("존재 하지 않는 데이터 이다");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        // 데이터가 있다면 삭제하고 응답
//        articleRepository.delete(article);

        //return ResponseEntity.status(HttpStatus.OK).body(null);
        //return ResponseEntity.status(HttpStatus.OK).build();  // 위와 동일한 방식

        return (deleted != null) ? ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/api/transction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
        List<Article> createList = articleService.createArticles(dtos);

        return (createList != null) ? ResponseEntity.status(HttpStatus.OK).body(createList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}