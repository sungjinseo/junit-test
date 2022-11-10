package dev.greatseo.template.api.sign.service.impl;

import dev.greatseo.template.api.member.domain.entity.Member;
import dev.greatseo.template.api.member.domain.repository.MemberRepository;
import dev.greatseo.template.api.sign.dto.SignInReqeustDto;
import dev.greatseo.template.api.sign.dto.SignInResponseDto;
import dev.greatseo.template.api.sign.dto.SignUpRequestDto;
import dev.greatseo.template.api.sign.service.SignService;
import dev.greatseo.template.exception.custom.DuplicatedException;
import dev.greatseo.template.exception.custom.ForbiddenException;
import dev.greatseo.template.exception.custom.UserNotFoundException;
import dev.greatseo.template.validation.Empty;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("signService")
@RequiredArgsConstructor
public class SignServiceImplement implements SignService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    public Boolean signUpMember(SignUpRequestDto joinDto) {

        // 아이디 중복체크
        if (!Empty.validation(memberRepository.countByEmail(joinDto.getEmail())))
            throw new DuplicatedException("Duplicated ID");

        // 연락처 중복체크
        if (!Empty.validation(memberRepository.countByMobile(joinDto.getMobile())))
            throw new DuplicatedException("Duplicated Mobile");

        // 비밀번호 암호화처리
        joinDto.setPassword(passwordEncoder.encode(joinDto.getPassword()));

        // 데이터 등록(저장)
        memberRepository.save(joinDto.toEntity());

        return true;
    }

    public SignInResponseDto signInMember(SignInReqeustDto loginDto) {

        // dto -> entity
        Member loginEntity = loginDto.toEntity();

        // 회원 엔티티 객체 생성 및 조회시작
        Member member = memberRepository.findByEmail(loginEntity.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));

        if (!passwordEncoder.matches(loginEntity.getPassword(), member.getPassword()))
            throw new ForbiddenException("Passwords do not match");

        // 회원정보를 인증클래스 객체(authentication)로 매핑
        return modelMapper.map(member, SignInResponseDto.class);
    }
}