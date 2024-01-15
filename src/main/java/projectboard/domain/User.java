package projectboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private Long id;
    private String userId;
    private String userPw;
    private String userName;
    private String email;
    private LocalDateTime createdAt;

    public User(String userId, String userPw, String userName, String email) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.email = email;
    }
}
