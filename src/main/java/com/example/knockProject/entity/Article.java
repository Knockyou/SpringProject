package com.example.knockProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

// 엔티티 역할을한다
@Entity // 해당 클래스가 엔티티타입을 명시, 테이블 이 생성
//@Table(neme="MuneBoard")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Slf4j
public class Article { // 테이블 명
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 id를 1씩 증가
    private Long id;

    @Column // 컬럼명을 명시해주는 어노테이션
    private String title;
    @Column
    private String content;

    public void patch(Article article){
        //article.title null이 아니면, this, title 업데이트
        if(article.title != null){
            log.info("=================================");
            log.info("this.title : " + this.title + ", article.title : " + article.title);
            this.title = article.title;
        }

        if(article.content != null){
            log.info("=================================");
            log.info("this.content : " + this.content + ", article.content : " + article.content);
            this.content = article.content;
        }
    }
}
