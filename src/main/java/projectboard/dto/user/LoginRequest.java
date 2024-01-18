package projectboard.dto.user;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String userId;
    private String userPw;
}
