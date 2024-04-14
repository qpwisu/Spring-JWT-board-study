package com.hany.studybasic.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.hany.studybasic.enum_class.UserRole;
import com.hany.studybasic.domain.entity.User;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class UserJoinRequest {

    @NotBlank(message = "로그인 아이디가 비어있습니다.")
    private String loginId;

    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String password;
    private String passwordCheck;

    @NotBlank(message = "닉네임이 비어있습니다.")
    private String nickname;

    // 비밀번호 암호화 X
    public User toEntity() {
        return User.builder()
                .loginId(this.loginId)
                .password(this.password)
                .nickname(this.nickname)
                .userRole(UserRole.USER)
                .build();
    }

    // 비밀번호 암호화
    public User toEntity(String encodedPassword) {
        return User.builder()
                .loginId(this.loginId)
                .password(encodedPassword)
                .nickname(this.nickname)
                .userRole(UserRole.USER)
                .build();
    }
}
