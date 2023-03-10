package com.jojoldu.book.springiscoming.web;


import com.jojoldu.book.springiscoming.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// @RestController는 컨트롤러를 json으로 반환하는 컨트롤러로 만들어 줌
@RestController
public class HelloController {
    // @GetMapping는 HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어줌
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    //@RequestParam는 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
}

