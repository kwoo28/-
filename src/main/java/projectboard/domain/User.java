package projectboard.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.sql.Timestamp;

@Getter @Setter
@NoArgsConstructor
public class User {
    @Schema(example = "유저 고유 번호")
    private Long id;
    @Schema(example = "유저 아이디")
    private String userId;
    @Schema(example = "유저 비밀번호")
    private String userPw;
    @Schema(example = "유저 닉네임")
    private String userName;
    @Schema(example = "유저 이메일")
    private String email;
    @Schema(example = "유저 생성 일자")
    private Timestamp createdAt;

    @Builder
    public User(String userId, String userPw, String userName, String email) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.email = email;
    }
}
