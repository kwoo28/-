package projectboard.dto.user;

import lombok.Getter;

@Getter
public class UpdateUserReqDto {
    private String userPw;
    private String userName;
    private String email;
}
