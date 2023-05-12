package swacademy.wbbackend.domain.member;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import swacademy.wbbackend.domain.review.Review;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MemberCreateForm {

    @NotBlank(message = "{NotBlank.member.username}")
    private String username;

    @NotBlank(message = "Password 는 필수입력입니다.")
    private String password;

}
