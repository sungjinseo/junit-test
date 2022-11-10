package dev.greatseo.template.api.sign.service;

import dev.greatseo.template.api.sign.dto.SignUpRequestDto;
import dev.greatseo.template.api.sign.dto.SignInReqeustDto;
import dev.greatseo.template.api.sign.dto.SignInResponseDto;

public interface SignService {

    Boolean signUpMember(SignUpRequestDto joinDto);

    SignInResponseDto signInMember(SignInReqeustDto loginDto);
}
