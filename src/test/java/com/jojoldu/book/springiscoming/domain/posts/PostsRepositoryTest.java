package com.jojoldu.book.springiscoming.domain.posts;

// save와 findAll 기능 테스트
// 실제로 생성된 쿼리를 로그로 보는 방법은 resources 폴더 아래 파일로. 스프링 부트에서는 application.properties , application.yml 등 파일로 한줄의 코드로 설정할 수 있게 지원하고 권장
import org.junit.After;     // Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정. 배포전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        Posts buildPort = Posts.builder()        // postsRepository.save : 테이블 posts에 insert/update 쿼리 실행, id값이 있다면 update, 없다면 insert 실행.
                .title(title)
                .content(content)
                .author("jojoldu@gmail.com")
                .build();
        postsRepository.save(buildPort);

        //when
        List<Posts> postsList = postsRepository.findAll();// 테이블 posts 에 있는 모든 데이터 조회 메소드

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2023,2,27,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postsList =postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>>>> createDate="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());

        // 위에서 설정한 now 보다 이후이면 테스트 통과
        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
         }
}
