package projectboard.dto.user;

import lombok.Getter;

@Getter
public class CreateUserReqDto {
    private String userId;
    private String userPw;
    private String userName;
    private String email;
}
