package com.example.knockProject.dto;

import com.example.knockProject.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(comment.getId(), // 댓글의 index
                              comment.getArticle().getId(), // 댓글리 바라보는 게시글의 id
                              comment.getNickname(), // 댓글 작성자
                              comment.getBody()); // 댓글 내용
    }
}
