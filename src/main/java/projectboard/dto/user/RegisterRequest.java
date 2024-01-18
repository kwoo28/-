package projectboard.dto.user;

import lombok.Getter;

@Getter
public class RegisterRequest {
    private String userId;
    private String userPw;
    private String userName;
    private String email;
}
