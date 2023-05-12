package basicprojectlab.wbbackend.domain.member;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateForm {

    @NotBlank(message = "{NotBlank.member.username}")
    private String username;

    @NotBlank(message = "Password 는 필수입력입니다.")
    private String password;

}
