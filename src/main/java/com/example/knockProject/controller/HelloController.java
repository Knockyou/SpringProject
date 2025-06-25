package com.example.knockProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// 컨트롤러라는 것을 알려 줘야 한다
// @은 에너테이션이라고 한다.
// 일종의 메타 데이터라고 한다.
// 요청을 하면 이쪽으로 들어 온다는 것
// 필요에따라 클래스가 아닌 함수에다가도 사용할수 있다
@Controller
public class HelloController {
    // 변수는 직관적으로 이해가능한 언어를 활용
    // 리펙토링을 할때 편리하다
    // 리펙토링 - 새로운 라이브러리 또는 기능적으로 분리 및 병합에 필요

    // url요청을 받는 주소를 적어 줄수 있ㄸ
    @GetMapping("/hello") // url에서 접근하는 문자열
    // 따라서 함수명은 그리 중요 하지 안다 에노테이션에서 넘어온 값을 통해
    // 함수를 실행하기 떄문이다.
    public String hello(Model model){
        System.out.println("컨트롤러 넘어옴");

        model.addAttribute("username", "김개똥");
        // 머스테치를 호출하는 방법
        // 리턴시 파일명을 넘겨준다
        return "hello";
    }

    @GetMapping("/seeyou")
    public String seeyou(Model model){

        model.addAttribute("username", "김개똥");

        return "seeyou";
    }


}
