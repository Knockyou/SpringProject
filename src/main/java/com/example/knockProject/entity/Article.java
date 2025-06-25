package com.example.knockProject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

// 엔티티 역할을한다
@Entity // 해당 클래스가 엔티티타입을 명시, 테이블 이 생성
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Article { // 테이블 명
    @Id
    @GeneratedValue // 자동으로 id를 1씩 증가
    private Long id;

    @Column // 컬럼명을 명시해주는 어노테이션
    private String title;
    @Column
    private String content;
}
