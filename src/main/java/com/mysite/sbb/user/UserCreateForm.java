package com.mysite.sbb.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {

    @NotEmpty(message = "사용자 ID는 필수 항목입니다.")
    @Pattern(regexp = "^$|^.{3,25}$", message = "사용자 ID는 3자 이상 25자 이하여야 합니다.")
    private String username;

    @NotEmpty(message = "닉네임은 필수 항목입니다.")
    private String nickname;

    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인은 필수 항목입니다.")
    private String password2;
}
