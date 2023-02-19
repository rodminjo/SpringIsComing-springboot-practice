package com.jojoldu.book.springiscoming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 앞으로 만들 프로젝트의 메인 클래스
// 아래 @springbootApplication 으로 인해 스프링부트 자동설정 bean읽기 생성을 자동으로 설정
// 이 어노테이션 위치부터 설정을 읽어가기 때문에 이 클래스는 항상 프로젝트 최상단에 위치
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // 이 run 메소드로 인해 내부 WAS(웹 어플리케이션 서버)를 실행한다.
        // 이렇게 되면 항상 서버에 톰캣을 설치할 필요가 없고 스프링부트로 만들어진 JAR 파일만 실행하면 됨.
        SpringApplication.run(Application.class, args);
    }
}
