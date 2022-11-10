package dev.greatseo.template.api.sign.dto;

import dev.greatseo.template.api.member.domain.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
public class SignInReqeustDto {

    @NotBlank(message = "'email' is a required input value")
    @Email(message = "It is not in email format")
    private String email;

    @NotBlank(message = "'password' is a required input value")
    private String password;


    public Member toEntity() {
        Member build = Member.builder()
                .email(email)
                .password(password)
                .build();

        return build;
    }

}
