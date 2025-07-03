package com.example.knockProject.entity;

import com.example.knockProject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name="article_id") // 외래키 선언, Article 엔티티의 id와 매핑작업
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

    public static Comment createComment(CommentDto commentDto, Article article) {
        // 엔티티로 반환을 하는데,

        // 변환이 불가할 경우 예외 처리

        if(commentDto.getId() != null)
            throw new IllegalArgumentException("댓글의 id가 존재하여 생성에 실패하였습니다");

        if(commentDto.getArticleId() != article.getId())
            throw new IllegalArgumentException("요청 id가 잘못되어 생성에 실패하였습니다");

        return new Comment(
                commentDto.getId(), // null을 넣어도 상관 없음 제너레이트가 부여 하기 때문이다.
                article,
                commentDto.getNickname(),
                commentDto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        if(this.id != dto.getId())
            throw new IllegalArgumentException("잘못된 id의 입력으로 예외를 발생시킨다.");

        if(dto.getNickname() != null)
            this.nickname = dto.getNickname();
        if(dto.getBody() != null)
            this.body = dto.getBody();
    }
}
