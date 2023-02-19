package com.jojoldu.book.springiscoming.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// ibatis 등에서 Dao라 불리는 DB Layer 접근자, JPA에선 Repository라 불리며 인터페이스로 생성
// 인터페이스를 생성하고 JpaRepository<entity 클래스, PK타입> 을 상속하면 기본적이 CRUD 메소드가 자동으로 생성됨.
// 주의점은 Entity 클래스와 기본 Entity Repository 가 함께 위치해야 함.
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
