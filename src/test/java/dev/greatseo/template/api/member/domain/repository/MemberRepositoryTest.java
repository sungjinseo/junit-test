package dev.greatseo.template.api.member.domain.repository;

import dev.greatseo.template.api.member.domain.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest  // DB와 관련된 컴포넌트만 메모리에 로딩
class MemberRepositoryTest {

    @Autowired  //
    // DI Test시만 autowired 사용 일반적으로 @ArgContsruct
    private MemberRepository memberRepository;

    @Test
    @DisplayName("멤버 등록 테스트")
    public void 멤버_등록_test(){
        // given
        String name = "서성진";
        String email = "sjseo85@gmail.com";
        String nickname = "greatseo";
        String password = "greatseo";
        String mobile = "01066850501";

        Member member = Member.builder()
                .name(name)
                .email(email)
                .nickname(nickname)
                .password(password)
                .mobile(mobile)
                .build();

        //insert 되기 전(persist 되기전) isDeleted 값이 null 이면 오류발생
        //when
        Member memberPS = memberRepository.save(member);

        //then
        assertEquals(name, memberPS.getName());
    }

    //@Test
    void findByEmail() {
    }

    //@Test
    void countByEmail() {
    }

    //@Test
    void countByMobile() {
    }
}