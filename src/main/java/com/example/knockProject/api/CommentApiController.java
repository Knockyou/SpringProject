package com.example.knockProject.api;

import com.example.knockProject.Service.CommentService;
import com.example.knockProject.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    // R - 조회  GET , "/articles/articleId/comments"
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        // 서비스에게 일 시키기
        List<CommentDto> dtos = commentService.comments(articleId);
        //응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    // C - 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable Long articleId,
            @RequestBody CommentDto commentDto){

        // 서비스에게 일 시키기
        CommentDto dto = commentService.createComment(articleId, commentDto);

        // 응답
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    // U - 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long id, @RequestBody CommentDto commentDto){
        //서비스 일 시킴
        CommentDto dto = commentService.updateComment(id, commentDto);

        // 응답
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    // D - 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> deleteComment(@PathVariable Long id){
        //서비스 일 시킴
        CommentDto dto = commentService.deleteComment(id);

        // 응답
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

}
