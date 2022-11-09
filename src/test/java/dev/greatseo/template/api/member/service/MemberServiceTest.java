package dev.greatseo.template.api.member.service;

import dev.greatseo.template.api.member.domain.entity.Member;
import dev.greatseo.template.api.member.domain.repository.MemberRepository;
import dev.greatseo.template.api.member.dto.MemberResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@DataJpaTest  // DB와 관련된 컴포넌트만 메모리에 로딩
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;

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
    void 멤버아이디검색서비스_test() {
        //given
        Long id = 1L;
        String email = "sjseo85@gmail.com";

        //when
        Member memberPS = memberRepository.findById(id).orElseGet(Member::new);
        //Member entity = memberRepository.findById(id)
        //        .orElseThrow(() -> new NullPointerException("Member Not Found"));
        System.out.println(memberPS.getEmail());


        //then
        ModelMapper modelMapper = new ModelMapper();
        MemberResponseDto resultDto = modelMapper.map(memberPS, MemberResponseDto.class);
        assertEquals(email,resultDto.getEmail());

    }
}