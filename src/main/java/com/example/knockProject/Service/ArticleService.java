package com.example.knockProject.Service;

import com.example.knockProject.Repository.ArticleRepository;
import com.example.knockProject.dto.ArticleForm;
import com.example.knockProject.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service // 서비스 객체를 생성
@Slf4j
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> articles() {
        return articleRepository.findAll();
    }

    public Article article(Long id){
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if(article.getId() != null)
            return null;
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        Article article = dto.toEntity();
        log.info("url_id = " + id + "," + article.toString());
        Article dbData = articleRepository.findById(id).orElse(null);

        if(dbData == null || id != article.getId()){
            log.info("잘못된 요청으로 인한 로그 남김");
            log.info("url_id = " + id + ", " + article.toString());
            return null;
        }
        dbData.patch(article);
        return articleRepository.save(dbData);
    }

    public Article delete(Long id) {
        Article deleted = articleRepository.findById(id).orElse(null);
        if(deleted == null)
            return null;
        articleRepository.delete(deleted);
        return deleted;
    }

    @Transactional // SpringFrameWork 프로세스중 예외가 발생 했을때는 디비에 저장이 되지 않는다
    // 디비에 연결된 함수는 필수적으로 해야한다
    // 롤백을 한다.
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // 복수의 dto -> 복수의 entity로 변환

        // for문 활용
//        List<Article> articleList = new ArrayList<>();
//        for(int i = 0; i < dtos.size(); i++){
//            ArticleForm dto = dtos.get(i);
//            Article entity = dto.toEntity();
//            articleList.add(entity);
//        }

        // 스트림 문법으로 변경
        // 스트림은 끊김이 없이 연결되고 연결되는것
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList()); // 나중에 toList로 묶었다는 문법


        // 복수의 entity를 db에 저장
//        for(int i = 0; i < articleList.size(); i++){
//            Article article = articleList.get(i);
//            articleRepository.save(article);
//        }
        // 스트림문법으로 변경
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

        // 강제로 예외상황 만들기, 롤백, @Transactional 테스트용
        articleRepository.findById(-1L).orElseThrow(() -> new IllegalArgumentException("결제에 실패"));

        // 리턴
        return articleList;
    }
}
