package dev.greatseo.template.api.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberResponseDto {

    private Long id;

    private String email;

    private String password;

    private String name;

    private String mobile;

    private String nickname;

    private String profile;

    private String isDeleted;

}
