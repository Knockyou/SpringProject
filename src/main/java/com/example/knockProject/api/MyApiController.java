package com.example.knockProject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // rest api 클래스로 명시한것
public class MyApiController {
    @GetMapping("/api/hello")
    public String hello()
    {

        return "Thank you";
    }
}
