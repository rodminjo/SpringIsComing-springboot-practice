package com.jojoldu.book.springiscoming.domain.posts;

import com.jojoldu.book.springiscoming.domain.BaseTimeEntity;
import lombok.Builder;      // 해당 클래스의 빌더 패턴 클래스 생성. 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함.
import lombok.Getter;       // getter 자동생성 어노테이션
import lombok.NoArgsConstructor;        // 기본생성자(public Posts(){}) 자동생성

// JPA 라이브러리. 주요 어노테이션을 클래스에 가깝게 두기 .책에선 javax , 현재는 상표권으로 인해 jakarta로 변경됨.
import javax.persistence.Column;      // 테이블의 칼럼을 나타내며 굳이 선언하지 않아도 해당 클래스의 필드는 모두 칼럼이 됨. 기본값외에 추가로 변경이 필요한 옵션이 있으면 사용(기본값 : VARCHAR(255), 사이즈를 늘리거나(title) 타입변경(content))
import javax.persistence.Entity;      // 테이블과 링크될 클래스임을 나타냄. 기본값으로 클래스의 카멜케이스 이름을 예시와 같이 변경함(PostEx.java -> post_ex table)
import javax.persistence.GeneratedValue;  //PK 생성규칙을 나타냄. 스프링부트 2.0에선 GenerationType.IDENTITY 옵션을 추가해야 auto_increment가 됨.
import javax.persistence.GenerationType;
import javax.persistence.Id;      // 해당 테이블의 PK필드를 나타냄
@Getter
@NoArgsConstructor
@Entity     // 웬만하면 Entity의 PK는 Long 타입의 Auto_imcrement 추천. 주민번호같은 유니크 키나 여러 키를 조합한 복합키로 pk를 잡으면 난감할 수 있음.

// Posts 클래스는 Entity 클래스로서 절대 Setter 메소드를 만들지 않음. 클래스의 인스턴스값들이 언제 어디서 변하는지 코드상으로 명확히 구분할 수 없기 때문.
// 필드값 변경이 필요하면 Setter 대신 의도를 명확히 나타낼 수 있는 메소드(ex. cancelOrder())를 추가할 것
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
