package com.jojoldu.book.springiscoming.web.dto;

// junit.assertThat vs AssertJ
// coreMatchers 와 달리 추가적으로 라이브러리가 필요하지 않음, 자동완성이 좀 더 확실하게 지원됨.
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트(){
        //given
        String name = "test";
        int amount = 10;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        // assertThat : assertj라는 테스트 검증 라이브러리의 검증 메소드, 검증하는 대상을 메소드 인자로 받으며 연달아 isEqualTo 같은 메소드 사용가능
        // isEqualTo : assertj의 동등비교 메소드 , assertThat에 있는 값과 isEqualTo 값을 비교해서 같을때만 성공
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }

}
