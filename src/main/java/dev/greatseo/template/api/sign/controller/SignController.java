package dev.greatseo.template.api.sign.controller;

import dev.greatseo.template.api.sign.dto.SignUpRequestDto;
import dev.greatseo.template.api.sign.dto.SignInReqeustDto;
import dev.greatseo.template.api.sign.dto.SignInResponseDto;
import dev.greatseo.template.api.sign.service.SignService;
import dev.greatseo.template.util.auth.AuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/api/v1/sign"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class SignController {

    private final SignService apiSignService;

    private final AuthProvider authProvider;

    /**
     * @method 설명 : 회원가입
     * @param joinDto
     * @throws Exception
     */
    @PostMapping(value = {"/signup"})
    public ResponseEntity<Boolean> appSignUp(@Valid @RequestBody SignUpRequestDto joinDto) throws Exception {

        return ResponseEntity.ok()
                .body(apiSignService.signUpMember(joinDto));
    }

    /**
     * @method 설명 : 로그인
     * @param loginDto
     * @throws Exception
     */
    @PostMapping(value = {"/signin"})
    public ResponseEntity<SignInResponseDto> appSignIn(@Valid @RequestBody SignInReqeustDto loginDto) throws Exception {

        SignInResponseDto authentication = apiSignService.signInMember(loginDto);

        return ResponseEntity.ok()
                .header("Accesstoken", authProvider
                        .createToken(
                                authentication.getId(),
                                authentication.getEmail(),
                                "USER"))
                .body(authentication);
    }
}
