package dev.greatseo.template.api.member.domain.repository;

import dev.greatseo.template.api.member.domain.entity.Member;
import dev.greatseo.template.domain.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest  // DB와 관련된 컴포넌트만 메모리에 로딩
class MemberRepositoryTest {

    @Autowired  //
    // DI Test시만 autowired 사용 일반적으로 @ArgContsruct
    private MemberRepository memberRepository;

    // [ 데이터준비() + 1 책등록 ] (T) , [ 데이터준비() + 2 책목록보기 ] (T)
    @BeforeEach
    public void 데이터준비() {
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

        memberRepository.save(member);
    }



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



    @Test
    void 이메일로사용자조회_test() {
        //given
        String email = "sjseo851@gmail.com";
        //when
        Optional<Member> memberPS = memberRepository.findByEmail(email);
        Member mem = memberPS.orElseGet(Member::new);
        if (mem.getEmail() == null){
            // 없는 경우도 새로운 생성자로 리턴이 되니깐 오류가 나지않음
            // 근데 여기서도 확인 할 수 있는게 있지 않을까?
        }else{
            assertEquals(email, mem.getEmail());
        }

        //1. memberSErvice객체에 findByid 메소드를 1L값으로 호출하면 Optional.of(member) 객체를 리턴하도록 Stubbing
        //when(memberService.findById(1L)).thenReturn( Optional.of(member));

        //2. studyRepository 객체에 save 메소드를 study 객체로 호출하면 study 객체 그대로 리턴하도록 study;
        //Study study = new Study(10, "test");
        //when(studyRepository.save(study)).thenReturn(study);
        //then
    }

    @Test
    void 이메일카운트쿼리검증_test() {
        //given
        String email = "sjseo85@gmail.com";
        //when
        Integer result = memberRepository.countByEmail(email);
        //then
        assertEquals(result, 1);
    }

    @Test
    void 휴대폰카운트쿼리검증_test() {
        //given
        String mobile = "01066850501";
        //when
        Integer result = memberRepository.countByMobile(mobile);
        //then
        assertEquals(result, 1);
    }
}