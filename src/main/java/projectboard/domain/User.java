package projectboard.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
//https://kim-jong-hyun.tistory.com/49
public class User {
    @ApiModelProperty(example = "유저 고유 번호")
    private Long id;
    @ApiModelProperty(example = "유저 아이디")
    private String userId;
    @ApiModelProperty(example = "유저 비밀번호")
    private String userPw;
    @ApiModelProperty(example = "유저 닉네임")
    private String userName;
    @ApiModelProperty(example = "유저 이메일")
    private String email;
    @ApiModelProperty(example = "유저 생성 일자")
    private LocalDateTime createdAt;

    @Builder
    public User(String userId, String userPw, String userName, String email) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.email = email;
    }
}
