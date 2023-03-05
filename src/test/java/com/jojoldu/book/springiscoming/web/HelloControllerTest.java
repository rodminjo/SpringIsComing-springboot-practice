package com.jojoldu.book.springiscoming.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// 테스트를 진행할때 JUnit에 내장된 실행자 외에 다른 실행자(SpringRunner) 실행, 스프링부트 테스트와 JUnit사이 연결자 역할을 함.
@RunWith(SpringRunner.class)

//Web(Spring MVC)에 집중할 수 있는 어노테이션, @Controller, @ControllerAdvice 사용가능
//하지만 @Service, @Component, @Repository 는 사용불가
// api 까지 작성할 경우 테스트가 실패함. 아래처럼 helloController로 범위를 지정해주고 메인 클래스 가서 jpa 어노테이션 주석처리해주면 다시 테스트됨
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    // 스프링이 관리하는 빈(Bean) 주입 받음
    @Autowired
    // 웹 API 테스트할때 사용, 스프링 MVC테스트 시작점
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        // MockMvc를 통해 /hello 주소로 HTTP GET 요청을 함
        // 아래 여러 검증기능을 선언 하여 mvc.perform 검증 가능
        // andExpect(status().isOk()) : HTTP Header의 Status를 검증, 200,404,500 등 상태를 검증. 여기선 OK 즉 200인지 아닌지 검증
        // andExpect(content().string(hello)) : 응답 본문의 내용을 검증, Controller 가 "hello"를 리턴하는것을 검증
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void HelloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                .param("name", name)        // param : API 테스트 할때 사용될 요청 파라미터 설정. 단, 값은 String만 허용(다른 타입이면 String으로 변환)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))     // jsonPath : JSON응답값을 필드별로 검증하는 메소드. $를 기준으로 필드명 명시
                .andExpect(jsonPath("$.amount",is(amount)));
    }
}
