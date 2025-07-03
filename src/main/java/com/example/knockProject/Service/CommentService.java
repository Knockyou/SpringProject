package com.example.knockProject.Service;

import com.example.knockProject.Repository.ArticleRepository;
import com.example.knockProject.Repository.CommentRepository;
import com.example.knockProject.dto.CommentDto;
import com.example.knockProject.entity.Article;
import com.example.knockProject.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        // 댓글을 DB 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);

        // 엔티티 -> DTO 변환
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for(int i = 0; i < comments.size(); i++){
//            Comment comment = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(comment);
//            dtos.add(dto);
//        }

//        List<CommentDto> dtos = commentRepository.findByArticleId(articleId)
//                .stream()
//                .map(comment -> CommentDto.createCommentDto(comment)).toList();

        // 리턴
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional // 저장 후 에러 발생 시 롤백
    public CommentDto createComment(Long articleId, CommentDto commentDto) {
        // 게시글이 존재하는지 조회
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재 하지 않습니다"));

        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(commentDto, article);

        // 엔티티를 DB에 저장
        Comment saved = commentRepository.save(comment);
        
        //DTO로 변환 후 반환
        return CommentDto.createCommentDto(saved);
    }

    @Transactional
    public CommentDto updateComment(Long id, CommentDto commentDto) {
        // 댓글 조회
        Comment saved = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("수정할 댓글이 존재 하지 않습니다.")); // () -> return         // 이름 함수를 만들고 -> return을 실행 할거다.

        // 댓글 수정
        saved.patch(commentDto);

        // 디비 업데이트
        Comment updated = commentRepository.save(saved);

        // dto 변화 후 응답
        return commentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto deleteComment(Long id) {
        // 댓글 조회
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("삭제할 댓글이 없슴니다"));
        // 댓글 삭제
        commentRepository.delete(comment);

        // 삭제 댓글 DTO로 변환 및 반환
        return CommentDto.createCommentDto(comment);
    }

    // R - 조회 - URL 설계
    // C - 생성

    // U - 수정
    // D - 삭제

}
