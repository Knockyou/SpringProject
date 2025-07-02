package com.example.knockProject.Service;

import com.example.knockProject.Repository.ArticleRepository;
import com.example.knockProject.dto.ArticleForm;
import com.example.knockProject.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 서비스 객체를 생성
@Slf4j
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> articles() {
        return articleRepository.findAll();
    }

    public Article article(Long id){
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if(article.getId() != null)
            return null;
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        Article article = dto.toEntity();
        log.info("url_id = " + id + "," + article.toString());
        Article dbData = articleRepository.findById(id).orElse(null);

        if(dbData == null || id != article.getId()){
            log.info("잘못된 요청으로 인한 로그 남김");
            log.info("url_id = " + id + ", " + article.toString());
            return null;
        }
        dbData.patch(article);
        return articleRepository.save(dbData);
    }

    public Article delete(Long id) {
        Article deleted = articleRepository.findById(id).orElse(null);
        if(deleted == null)
            return null;
        articleRepository.delete(deleted);
        return deleted;
    }
}
