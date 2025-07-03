package com.example.knockProject.Repository;

import com.example.knockProject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 나에게 최적합의 쿼리문을 직접 생성
    // 이와 같이 직접 퀴리를 생성하는 것을 네이티브 쿼리문(native query method)
    // 레포지터리 안에 선언하여 해당 레포를 DI 하는 모듯 곳에서 활용 가능
    // 네이티브 쿼리를 만드는 방법 2가지 : 1.어노테이션 활용, 2.xml(외부파일)

    // 특정 게시글의 모든 댓글 조회
    @Query(value = "SELECT * FROM Comment WHERE article_id = :articleId", nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);

    // 특정 닉네임의 모든 댓글 조회
    List<Comment> findByNickname(String nickname);





}
