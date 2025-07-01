package com.example.knockProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// 엔티티 역할을한다
@Entity // 해당 클래스가 엔티티타입을 명시, 테이블 이 생성
//@Table(neme="MuneBoard")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Article { // 테이블 명
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 id를 1씩 증가
    private Long id;

    @Column // 컬럼명을 명시해주는 어노테이션
    private String title;
    @Column
    private String content;
}
