package com.example.knockProject.Repository;

import com.example.knockProject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

// *인터페이스
public interface ArticleRepository extends CrudRepository<Article, Long> {

    // 원본은 iterable형 이었는데
    // 재정의 해서 사용 했다.
    @Override
    ArrayList<Article> findAll();
}
