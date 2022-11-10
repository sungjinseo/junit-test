package dev.greatseo.template.api.member.service;

import dev.greatseo.template.api.member.domain.entity.Member;
import dev.greatseo.template.api.member.domain.repository.MemberRepository;
import dev.greatseo.template.api.member.dto.MemberResponseDto;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponseDto getMemberById(Long id) {

        ModelMapper modelMapper = new ModelMapper();
        Member entity = memberRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Member Not Found"));

        return modelMapper.map(entity, MemberResponseDto.class);
    }
}
