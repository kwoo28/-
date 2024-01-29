package projectboard.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateUserReqDto {
    @NotBlank(message = "아이디를 입력해주세요.")
    private String userId;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String userPw;
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String userName;
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;
}