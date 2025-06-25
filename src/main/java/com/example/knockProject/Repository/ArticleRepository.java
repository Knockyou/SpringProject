package com.example.knockProject.Repository;

import com.example.knockProject.entity.Article;
import org.springframework.data.repository.CrudRepository;

// *인터페이스
public interface ArticleRepository extends CrudRepository<Article, Long> {

}
